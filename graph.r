
#load data
times <- c(1.09,2.98,4.92,18.37,23.98,29.26,31.70,32.44,0.99,2.01,2.98,3.98,4.93,5.94,6.94,7.90,0.88,4.63,6.98,10.14,16.07,18.81,23.13,30.44,1.00,6.20,3.40,4.44,5.55,7.13,8.28,10.15,0.94,3.51,6.59,9.40,11.65,15.84,19.66,22.73,1.01,1.03,1.04,1.11,1.58,1.69,2.21,2.21,0.98,2.38,3.03,3.67,4.11,4.40,3.62,3.88,1.05,1.13,1.17,1.21,1.66,1.80,1.95,2.01,0.90,0.90,0.91,0.94,1.52,1.47,1.70,1.91,1.01,1.04,1.04,1.12,1.65,1.68,2.18,2.23)
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
		plot(example2, main="Example 2", ylab="Duration (seconds)", xlab="Threads", type="o")
	}
	else if(x=='3b'){
		plot(example3big, main="Example 3", ylab="Duration (seconds)", xlab="Threads", type="o")
	}
	else if(x=='3s'){
		plot(example3small, main="Example 3", ylab="Duration (seconds)", xlab="Threads", type="o")
	}
	else if(x=='4b'){
		plot(example4big, main="Example 4", ylab="Duration (seconds)", xlab="Threads", type="o")
	}
	else if(x=='5b'){
		plot(example5big, main="Example 5", ylab="Duration (seconds)", xlab="Threads", type="o")
	}
	else if(x=='6b'){
		plot(example6big, main="Example 6", ylab="Duration (seconds)", xlab="Threads", type="o")
	}
	else if(x=='7b'){
		plot(example7big, main="Example 7", ylab="Duration (seconds)", xlab="Threads", type="o")
	}
}

plotThread <- function(x){
	plot(times[thread==x], main=paste(x,"Threads"), ylab="Duration (seconds)", xlab="Threads")
}


