	echo package tutorial; > mediaplayer/src/tutorial/RPC.java 
	echo package tutorial; > mediaplayer/src/tutorial/ServerSelector.java
	type gen\client\RPC.java >> mediaplayer/src/tutorial/RPC.java
  type ServerSelector.java >> mediaplayer/src/tutorial/ServerSelector.java
