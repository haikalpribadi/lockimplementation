
#load data
times <- c(...)
example <- gl(5,16,80, labels=c('e3', 'e4', 'e5', 'e6', 'e7'))
thread <- gl(8,1,80)
array <- gl(2,8,80, labels=c('small','big'))

#extract data for each example
example2 <- c(0.99,1.01,1.04,1.07,1.63,1.66,2.02,2.21)

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
		plot(example2, main="Example 2", ylab="Duration (seconds)", xlab="Threads")
	}
	else if(x==3){
		plot(example3, main="Example 3", ylab="Duration (seconds)", xlab="Threads")
	}
	else if(x==4){
		plot(example4, main="Example 4", ylab="Duration (seconds)", xlab="Threads")
	}
	else if(x==5){
		plot(example5, main="Example 5", ylab="Duration (seconds)", xlab="Threads")
	}
	else if(x==6){
		plot(example6, main="Example 6", ylab="Duration (seconds)", xlab="Threads")
	}
	else if(x==7){
		plot(example7, main="Example 7", ylab="Duration (seconds)", xlab="Threads")
	}
}

plotThread <- function(x){
	plot(times[thread==x], main=paste(x,"Threads"), ylab="Duration (seconds)", xlab="Threads")
}


