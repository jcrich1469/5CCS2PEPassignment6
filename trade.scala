// Part 2 about Buy-Low-Sell-High using Yahoo Financial Data
//===========================================================


// (1) Complete the function that is given a list of floats
// and calculuates the indices for when to buy the commodity 
// and when to sell

//def trade_times(xs: List[Double]): (Int, Int) = ...

import scala.io.Source;
/*
def trade_times(xs: List[Double]): (Int, Int) = {
	
		
	var greatestPriceDifference = 0.0;
	var lowestPriceTime = 0;
	var highestPriceTime = 0;

	var i : Double = 0;
	var j : Double = 0;
	
	//var dI : Double = 0.0; 
	//var dJ : Double = 0.0; 

	for(i <- xs){// i is each individual element, a double.

		for(j <- xs){	
			
			//dI = i.toDouble;
			//dJ = j.toDouble;

			if((xs.indexOf(j)>xs.indexOf(i)) && (j-i>greatestPriceDifference)){


				lowestPriceTime = xs.indexOf(i);// now to get the index value of the list.
				highestPriceTime = xs.indexOf(j);
				greatestPriceDifference = j-i;			

			}
	
		}
	}
	
	return (lowestPriceTime, highestPriceTime);
	
}
*/

def trade_times(xs: List[Double]): (Int, Int) = {

	var lowestPriceTime : Int = xs.indexOf(xs.min);
	var highestPriceTime : Int = 0;
	var currentHighestPrice: Double = 0.0;
	
	var i : Int = lowestPriceTime;
	
	for(i <- lowestPriceTime to xs.length-1){// i is each individual element, a double.
		
		if(lowestPriceTime == xs.length-1){
			
			return (lowestPriceTime, lowestPriceTime);	
			
		}
		
		if(xs(i) > currentHighestPrice){
			
			currentHighestPrice = xs(i);
			highestPriceTime = i;
		}	
		
	}
	
	return (lowestPriceTime, highestPriceTime);
	
}

//println(trade_times(List(28.0, 22.0, 30.0, 14.0, 18.0)));


// an example
//val prices = List(28.0, 18.0, 20.0, 26.0, 24.0)
//assert(trade_times(prices) == (1, 3), "the trade_times test fails")


// (2) Complete the ``get webpage'' function that takes a
// a stock symbol as argument and queries the Yahoo server
// at
//      http://ichart.yahoo.com/table.csv?s=<<insert stock symbol>>
// 
// This servive returns a CSV-list that needs to be separated into
// a list of strings.

//def get_page(symbol: String): List[String] = ...
def get_page(symbol: String):  List[String] = {

	//val url = "http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=snl1"
	//Source.fromURL(url).mkString.drop(1).dropRight(2)
	//val url = "http://ichart.yahoo.com/table.csv?s=GOOG";
	
	val url = "http://ichart.yahoo.com/table.csv?s="+symbol;
 
	val page: String = Source.fromURL(url).mkString;//.drop(1);//.dropRight(2);
	// from this string repeatedly put into the list
	//return page;// drop(1) is just one space on the left, drop two is same, two characters from the right
	
	//val priceList: List[String] = page.split("\\n").map(_.trim).toList;
	
	val priceList: List[String] = page.split("\n").toList;
	
	//println(priceList);
	//var tradeindex: String = ""; 
	
	//val count:Int = 1;
	
	//var i : Int = 0;
	
	//val currentList: List[String] = priceList(1).split(",").toList;
	/*
	println(currentList(i));
	println(currentList(1));
	println(currentList);
	println(currentList.reverse);

	val rList: List[String] = currentList.reverse;
	var str : String = "";
	var dates : String = currentList(0);
	val rnList = rList.dropRight(1);
	println("dropping dates");
	println(rnList);
	str = rnList.mkString(",");
	println(str);
	str = dates+","+str;
	val veryNewList : List[String] = str.split(",").toList;
	println("reversedList");
	println(veryNewList);
	*/
	//val rList: List[String] = noDateList.reverse;
	//println(noDateList);
	//println(rList);
	
	/*
	if(symbol == "YHOO"){
		
		
		for(i <- 1 to priceList.length-1){// skip the first ln
			
			val currentList: List[String] = priceList(i).split(",").toList;
			val noDateList: List[String] = currentList.tail;
			val rList: List[String] = noDateList.reverse;
			val date = rList.right();
			rList.dropRight(1);
			//var toInsert : date+","+rnList.mkString();
			
			//for(i <- 0 to dataList.length-2){// date is at the end, so ignore.
				
				//toInsert = toInsert + "" +dataList(i);
	
			//
			//priceList(i) = toInsert.toList;

		}		
			
		
	}*/
	//priceList.foreach{println};
	return priceList;

}

//val list : List[String] = get_page("GOOG");

//println(list.mkString("\n"));
//var i : Int = 0;

//val yhList : List[String] = get_page("YHOO");
//println(yhList.mkString("\n"));
/*
println(list.length);

for(i <- 0 to list.length-1){

	println(list(i));

}


println(list.length);
for(i <- 0 to yhList.length-1){

	println(yhList(i));

}*/

//println(list(1));
//println(yhList(0));
//println(yhList(1));

// (3) Complete the function that processes the CSV list
// extracting the dates and anjusted close prices. The
// prices need to be transformed into Doubles.

//def process_page(symbol: String): List[(String, Double)] = ...
def process_page(symbol: String): List[(String, Double)] = {
	
	val priceList: List[String] = get_page(symbol);
	var currentList: List[String] = priceList(0).split(",").toList;
	var pairedList: List[(String,Double)] = List();
	//println(pairedList);
	
	//println(priceList(0));
	//println(priceList(1));

	//println(currentList);

	var date: String = "";

	var closePrice = 0.0;
	
	var i : Int = 0;
	for(i <- 1 to priceList.length-1){
			
		currentList = priceList(i).split(",").toList;// ignore the top.

		date = currentList.head;

		closePrice = currentList.last.toDouble;

		pairedList = (date,closePrice)::pairedList;

	}
	
	return 	pairedList;		
}


val list: List[(String, Double)] = process_page("GOOG");
//println(list);
//println(list.length);
//println(list(0));
//println(list(0));
// (4) Complete the query_company function that obtains the
// processed CSV-list for a stock symbol. It should return
// the dates for when to buy and sell the stocks of that company.
	
def query_company(symbol: String): (String, String) = {

	var datePrices : List[(String,Double)] = process_page(symbol);// removes....
	
	var pricesList : List[Double] = List();// removes the date.
	
	var currentPrice : Double = 0.0;

	var pair = ("", Double); 

	for(pair <- datePrices){
	
		currentPrice = pair._2;
 		pricesList = currentPrice::pricesList;
	}
	
	var lowHighIndex = (0,0);
	
	lowHighIndex = trade_times(pricesList.reverse);	
	
	var lowIndex = lowHighIndex._1;
	var highIndex = lowHighIndex._2;
	
	var lowPair = datePrices(lowIndex);
	var highPair = datePrices(highIndex);
	//println(lowPair+" "+highPair);
	return (lowPair._1,highPair._1);
	
}



// some test cases

//query_comp("GOOG")

// some more test cases

val indices = List("GOOG", "AAPL", "MSFT", "IBM", "FB", "YHOO", "AMZN", "BIDU")

for (name <- indices) {
  val times = query_company(name)
  println(s"Buy ${name} on ${times._1} and sell on ${times._2}")
}



