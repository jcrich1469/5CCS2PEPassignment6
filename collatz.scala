// Part 1 about the 3n+1 conceture
//=================================


//(1) Complete the collatz function below. It should
//    recursively calculate the number of steps needed 
//    until the collatz series reaches the number 1.
//    If needed you can use an auxilary function that
//    performs the recursion. The function should expect
//    arguments in the range of 1 to 1 Million.

//def collatz(n: Long): ... = ...


def collatz(n: Long): Long = {
	
	var steps: Long = 0;
	
	if(n == 1){
		
		steps += 1;;
		return steps;		
		
	}
	
	if(n % 2 == 0){
		
		steps = collatz(n/2);			
		
	} else {

		steps = collatz((3*n)+1);
		
	}
	
	return steps+1;
}

/*
collatz(6);
println(steps);
var num = 837799;
println(num);

collatz(20);
println(steps);
collatz(837799);
println(steps);
collatz(1000000);
println(steps);
*/

//println(collatz(837799));
//(2)  Complete the collatz bound function below. It should
//     calculuate how many steps are needed for each number 
//     from 1 upto a bound and returns the maximum number of
//     steps and the corresponding number that needs that many 
//     steps. You should expect bounds in the range of 1
//     upto 1 million. The first component of the pair is
//     the maximum number of steps and the second is the 
//     corresponding number.

def collatz_max(bnd: Int): (Int, Int) = {
	
	var maxStepsValue = 0;
	var maxCount = 0;
	var lwrbnd = 1;
	var i = lwrbnd;
			
	//var currentValue = 0;
	
	var currentValue : Long = 0;
	var count : Long = 1;
	//val loop = new Breaks;

	for(i <- lwrbnd to bnd){//1 upto the bound 1,2,3,4...bnd
		
		currentValue += 1;
		
		count = collatz(currentValue);


		if(count > maxCount){
			
			maxCount = count.toInt;
			maxStepsValue = i;// value at i.		
			
		}
		
		count = 1;		
		
	}

	return (maxCount,maxStepsValue);	

}

/*
println(collatz_max(9));
println(collatz_max(10));
println(collatz_max(100));
println(collatz_max(1000));
println(collatz_max(10000));
println(collatz_max(100000));
println(collatz_max(910107));
println(collatz_max(837799));
println(collatz_max(1000000));
*/

