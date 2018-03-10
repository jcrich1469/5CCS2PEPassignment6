// Advanvced Part 3 about really dump investing strategy
//=======================================================

//two test portfolios
import scala.io.Source;
import java.io.IOException;
import scala.util.Try;

val blchip_portfolio = List("GOOG", "AAPL", "MSFT", "IBM", "FB", "YHOO", "AMZN", "BIDU")// websites...
val rstate_portfolio = List("PLD", "PSA", "AMT", "AIV", "AVB", "BXP", "CBG", "CCI", 
                            "DLR", "EQIX", "EQR", "ESS", "EXR", "FRT", "GGP", "HCP") 


// (1) The function below should obtain the first trading price
// for a stock symbol by using the query
//
//    http://ichart.yahoo.com/table.csv?s=<<symbol>>&a=0&b=1&c=<<year>>&d=1&e=1&f=<<year>> 
// 
// and extracting the first January Adjusted Close price in a year.

def get_first_price(symbol: String, year: Int): Option[Double] = {
	
	//Option indicates that we may be dealing with a value of type double, or none. 
	// Scala never throws nullpointers etc. We may not have a value.
	
	val url = "http://ichart.yahoo.com/table.csv?s="+symbol+"&a=0&b=1&c="+year+"&d=1&e=1&f="+year
	
	
	val page = Try(Some(Source.fromURL(url).mkString)).getOrElse(None)
	
	//println(page)
	if(page != None){
		
		val priceList: List[String] = page.last.split("\n").toList;// split by Line
		val firstDate = priceList.last 
		//println(firstDate);
		
		val dateData: List[String] = firstDate.split(",").toList;
		
		val a : Option[Double] = Some(dateData.last.toDouble)
		a

	} else {
		
		None;			
			
	}
	// to return an option
	
}


// Complete the function below that obtains all first prices
// for the stock symbols from a portfolio for the given
// range of years


def get_prices(portfolio: List[String], years: Range): List[List[Option[Double]]] = {//List((year(prices))) i.e. (1978(£1,£2,£3))

	//val theYears = years;

	//var folioPrices : List[List[Option[Double]]] = ();

	//var prices : List[Option[Double]] = ();

	
	for(year <- years.toList) yield{
		
		for(company <- (0 until portfolio.length).toList) yield{
			
			get_first_price(portfolio(company),year)
			
		}

	}
		
}

// test case
//val p = get_prices(List("GOOG", "AAPL"), 2010 to 2012)
//println(p)

// (2) The first function below calculates the change factor (delta) between
// a price in year n and a price in year n+1. The second function calculates
// all change factors for all prices (from a portfolio).

def get_delta(price_old: Option[Double], price_new: Option[Double]): Option[Double] = {

  if (price_old.isDefined && price_new.isDefined) {  //checks whether is some or not
    Some((price_new.get - price_old.get) / price_old.get )  //if i put a val then option will be a type of unit
  }
  else None;
}


def get_deltas(data: List[List[Option[Double]]]):  List[List[Option[Double]]] = {

  for (column<-(0 until data.length-1).toList) yield{
    for (row<-(0 until data(column).length).toList) yield {// -2 means doesnt look at  the last element case
      get_delta(data(column)(row),data(column+1)(row)) //loop through the symbol and get their values for the deltas for the 
    }
  }
}

// test case using the prices calculated above
//println("getting deltas");
//val d = get_deltas(p)

//println(d)

/*
for(x <- 0 to 4-2){

	println(x);
	
}

for(x <- 0 until 4-1){

	println(x);
	
}
*/
// (3) Write a function that given change factors, a starting balance and a year
// calculates the yearly yield, i.e. new balanace, according to our dump investment 
// strategy. Another function calculates given the same data calculates the
// compound yield up to a given year. Finally a function combines all 
// calculations by taking a portfolio, a range of years and a start balance
// as arguments.

//def yearly_yield(data: List[List[Option[Double]]], balance: Long, year: Int): Long = ... 

//test case
//yearly_yield(d, 100, 0)

//def compound_yield(data: List[List[Option[Double]]], balance: Long, year: Int): Long = ... 

//def investment(portfolio: List[String], years: Range, start_balance: Long): Long = ...


//test cases for the two portfolios given above
//investment(rstate_portfolio, 1978 to 2016, 100)
//investment(blchip_portfolio, 1978 to 2016, 100)

