package tutorial;

//~--- non-JDK imports --------------------------------------------------------
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

//~--- JDK imports ------------------------------------------------------------
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;

import java.net.*;

import java.util.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Tutorial {

    private String myIP = "10.102.42.169";
    private final JFrame frame;
    private final AudioMediaPlayerComponent mediaPlayerComponent;
    private final JButton pauseButton;
    private final JButton rewindButton;
    private final Vector<String> ipAddresses;
    private final JButton skipButton;
    private final JList songList;
    private final DefaultListModel songsModel;
    private final JButton playButton;

    public Tutorial(String[] args) {
        frame = new JFrame("My First Media Player");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(e);
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });

        JPanel contentPane = new JPanel();

        contentPane.setLayout(new BorderLayout());
        mediaPlayerComponent = new AudioMediaPlayerComponent();
        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void stopped(MediaPlayer mediaPlayer) {
                ;
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
                ;
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                ;
            }
        });
        ipAddresses = new Vector();

        JPanel controlsPane = new JPanel();
        JPanel listPane = new JPanel();

        playButton = new JButton("Play");
        controlsPane.add(playButton);
        pauseButton = new JButton("Pause");
        controlsPane.add(pauseButton);
        rewindButton = new JButton("Rewind");
        controlsPane.add(rewindButton);
        skipButton = new JButton("Skip");
        controlsPane.add(skipButton);
        songsModel = new DefaultListModel();
        songList = new JList(songsModel);
        songList.setSize(100, 100);
        listPane.add(songList);
        contentPane.add(controlsPane, BorderLayout.SOUTH);
        contentPane.add(listPane, BorderLayout.NORTH);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.getMediaPlayer().pause();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Byte> b;

                try {
                    b = RPC.getSong((String) (songList.getSelectedValue()),
                            ipAddresses.get(songList.getSelectedIndex()));

                    File f = new File("media.mp3");
                    FileOutputStream fileStream = new FileOutputStream(f);
                    BufferedOutputStream bos = new BufferedOutputStream(fileStream);

                    for (int i = 0; i < b.size(); i++) {
                        bos.write(b.get(i));
                    }

                    bos.close();
                    fileStream.close();
                    mediaPlayerComponent.getMediaPlayer().playMedia("media.mp3");
                } catch (Throwable ex) {
                    Logger.getLogger(Tutorial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        rewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.getMediaPlayer().skip(-10000);
            }
        });
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.getMediaPlayer().skip(10000);
            }
        });
        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void playing(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.setTitle(String.format("My First Media Player - %s",
                                mediaPlayerComponent.getMediaPlayer().getMediaMeta().getTitle()));
                    }
                });
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        closeWindow();
                    }
                });
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(frame, "Failed to play media", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        closeWindow();
                    }
                });
            }
        });
        frame.setContentPane(contentPane);
        frame.setVisible(true);

        try {
            String[] fn = fileNames();
            Vector<String> v = new Vector();
            
            myIP = getOwnIP();
            System.out.println("IP detected to " + myIP);
            
            for (Integer i = 0; i < fn.length; i++) {
                v.addElement(fn[i]);
            }

            RPC.Register(v, myIP);
            System.out.println("finished registering");

            Vector<String> x, y;

            System.out.println("getting songs ");
            x = RPC.getSongsList();
            y = RPC.getIPList();
            ipAddresses.clear();
            System.out.println("getting songs call completed " + x.size() + " " + y.size());
            songsModel.clear();

            for (int i = 0; i < x.size(); i++) {
                songsModel.addElement(x.get(i));
                System.out.println("received " + x.get(i) + " at " + y.get(i));
                ipAddresses.add(y.get(i));
            }
        } catch (Throwable th) {
            System.out.println("some error");
            System.out.println(th);
        }

//      mediaPlayerComponent.getMediaPlayer().playMedia("media.mp4");
    }

    private static File[] finder() {
        File dir = new File("server//songs");

        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".mp3");
            }
        });
    }

    private static String getOwnIP() {
        try {
            InetAddress ownIP = InetAddress.getLocalHost();

            System.out.println("IP of my system is := " + ownIP.getHostAddress());

            return ownIP.getHostAddress();
        } catch (Exception e) {
            System.out.println("Exception caught =" + e.getMessage());
        }

        return "";
    }

    private static String[] fileNames() {
        File[] f = finder();
        String[] s = new String[f.length];

        for (Integer i = 0; i < f.length; i++) {
            s[i] = f[i].getName();
        }

        return s;
    }

    public static void main(final String[] args) {
        new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Tutorial(args);
            }
        });
    }

    private void closeWindow() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}