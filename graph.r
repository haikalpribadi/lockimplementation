
#load data
times <- c(
1.09,2.98,4.92,15.37,23.98,29.26,31.70,34.44,
0.99,2.01,2.98,3.98,4.93,5.94,6.94,7.90,
0.88,4.63,6.98,10.14,16.07,18.81,23.83,29.44,
1.00,2.57,3.40,4.44,5.55,7.13,8.28,10.15,
0.94,3.51,6.59,9.40,11.65,15.84,19.66,22.73,
1.01,1.03,1.04,1.11,1.58,1.69,2.21,2.21,
0.98,2.21,3.002,3.67,4.01,4.20,3.62,3.88,
1.05,1.13,1.17,1.21,1.56,1.80,1.95,2.01,
0.90,0.90,0.91,0.94,1.32,1.47,1.70,1.91,
1.01,1.04,1.04,1.12,1.51,1.68,1.98,2.23)
example <- gl(5,16,80, labels=c('e3', 'e4', 'e5', 'e6', 'e7'))
thread <- gl(8,1,80)
array <- gl(2,8,80, labels=c('small','big'))

#extract data for each example
example2 <- c(0.99,1.01,1.04,1.07,1.42,1.66,2.02,2.21)

example3 <- times[example=='e3']
example3big <- example3[array=='big']
example3small <- example3[array=='small']

example4 <- times[example=='e4']
example4big <- example4[array=='big']
example4small <- example4[array=='small']

example5 <- times[example=='e5']
example5big <- example5[array=='big']
example5small <- example5[array=='small']

example6 <- times[example=='e6']
example6big <- example6[array=='big']
example6small <- example6[array=='small']

example7 <- times[example=='e7']
example7big <- example7[array=='big']
example7small <- example7[array=='small']

plotExample <- function(x){
	if(x==2){
		plot(example2, main="Multithreading execution time", ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o")
	}
	else if(x==3){
		plot(example3small, main="Java Synchronization execution time", ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="blue")
		lines(example3big, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="black")
		legend(1, 33, c("array size = 5","array size = 5000"), col=c("blue","black"), pch=21:21, lty=1:1)
	}
	else if(x==4){
		plot(example4small, main="Test-and-test-and-set Mutex execution time", ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="blue")
		lines(example4big, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="black")
		legend(1, 28, c("array size = 5","array size = 5000"), col=c("blue","black"), pch=21:21, lty=1:1)
	}
	else if(x==5){
		plot(example5small, main="Test-and-test-and-set Reader-Writer lock execution time", ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="blue")
		lines(example5big, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="black")
		legend(1, 22, c("array size = 5","array size = 5000"), col=c("blue","black"), pch=21:21, lty=1:1)
	}
	else if(x==6){
		plot(example6small, main="Flag-based Reader-Writer lock execution time", ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="blue")
		lines(example6big, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="black")
		legend(1, 4.2, c("array size = 5","array size = 5000"), col=c("blue","black"), pch=21:21, lty=1:1)
	}
	else if(x==7){
		plot(example7big, main="Version Numbers execution time", ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), ylim=c(0.8,2.2), type="o", col="black")
		lines(example7small, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="blue")
		legend(1, 2.2, c("array size = 5","array size = 5000"), col=c("blue","black"), pch=21:21, lty=1:1)
	}
	else if(x==8){
		plot(example4big, main="Execution time of multiple lock implementations reading an array of size 5000", ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="orange")
		lines(example3big, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="red")
		lines(example5big, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="purple")
		lines(example6big, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="blue")
		lines(example7big, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="black")
		legend(1, 10, c("Java Synchronize","TTAS", "TTAS Reader-Writer", "Flag-based Reader-Writer", "Version Number"), col=c("red", "orange", "purple","blue","black"), cex=0.8, pch=21:21, lty=1:1)
	}
	else if(x==9){
		plot(example3small, main="Execution time of multiple lock implementations reading an array of size 5", ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="red")
		lines(example4small, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="orange")
		lines(example5small, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="purple")
		lines(example6small, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="blue")
		lines(example7small, ylab="Duration (seconds)", xlab="Threads", xlim=c(1,8), type="o", col="black")
		legend(1, 33, c("Java Synchronize","TTAS", "TTAS Reader-Writer", "Flag-based Reader-Writer", "Version Number"), col=c("red", "orange", "purple","blue","black"), cex=0.8, pch=21:21, lty=1:1)
	}
	
}

plotThread <- function(x){
	plot(times[thread==x], main=paste(x,"Threads"), ylab="Duration (seconds)", xlab="Threads")
}


