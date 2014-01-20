
#load data
times <- c()
thread <- gl(8,1,8)

#extract data for each example
example2 <- times[example=='e2']
example3 <- times[example=='e3']
example4 <- times[example=='e4']
example5 <- times[example=='e5']
example6 <- times[example=='e6']
example7 <- times[example=='e7']

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


