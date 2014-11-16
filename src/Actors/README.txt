SWEN-342 Engineering Of Concurrent & Distributed Software Systems
Concurrent Grep With Actors



CGrep.java is the point of entry, it can be called acoording to spec:
    java CGrep pattern [file . . .]

Notice we are using the latest version of Akka as of Nov 2014 which
is 2.10, which means we are using Props to instantiate the Actors.


Geoff Berl,
Conor Wright,
Kocsen Chung