import java.util.*;




public class BookTicket {
	
	public static String correctSeat(Scanner s)
	{
		System.out.println("Enter BC for Bussiness Class Ticket & Enter EC for Economy Ticket ");
		String seat = s.next();
		if(seat.equals("BC") || seat.equals("EC"))
		{
			return seat;
		}
		else
		{
			
			System.out.println("Invaild class tickets ");
			return correctSeat(s);
		}
		
	}
	public static int numberOfTickets(Flight currentFlight,Scanner s,String seatClass)
	{
		if(seatClass.equals("BC") && currentFlight.businessTicket > 0)
		{
			System.out.println("Total Ticket Avaiable in the Flight ID "+currentFlight.flightIds+" in BussinessClass is " + currentFlight.businessTicket);
			int t = s.nextInt();
			if(t <= currentFlight.businessTicket)
			{
				currentFlight.businessTicket = currentFlight.businessTicket - t;
				return t;
			}
			else
			{
				numberOfTickets(currentFlight,s,seatClass);
			}
		}
		else
		{
			if(currentFlight.economyTicket < 0)
			{
				System.out.println("no tickets available");
				return 0;
			}
			System.out.println("Total Ticket Avaiable in the Flight ID "+currentFlight.flightIds+" in EconomyClass is " + currentFlight.economyTicket);
			int t = s.nextInt();
			
			if(t <= currentFlight.economyTicket)
			{
				currentFlight.economyTicket = currentFlight.economyTicket -t;
				return t;
			}
			else
			{
				numberOfTickets(currentFlight,s,seatClass);
			}
			
		}
		return 0;
		
	}
	
	public static int[] arr(Flight currentFlight,int ticket,String seatClass)
	{
		System.out.println(currentFlight.flightIds);
		int[] arr1 = new int[ticket];
		if(seatClass.equals("BC"))
		{
			for(int i=0; i<ticket; i++)
			{
				arr1[i] = currentFlight.businessTicketPositions.get(0);
				currentFlight.businessTicketPositions.remove(0);
			}
		}
		else
		{
			for(int i=0; i<ticket; i++)
			{
				arr1[i] = currentFlight.economyTicketPositions.get(0);
				currentFlight.businessTicketPositions.remove(0);
			}
		}
		return arr1;
		
	}
	public static String numberOfMeals(Scanner s)
	{
		System.out.println("Enter Y means Yes are N means No");
		String c = s.next();
		if(c.equals("Y"))
		{
			return "YES";
		}
		else if(c.equals("N"))
		{
			return "NO";
		}
		else
		{
			System.out.println("Enter vaild character ");
			numberOfMeals(s);
		}
		return "";
		
	}
	public static int addTicketSeat(Flight currentFlight,int ticket, String seatClass)
	{
		if(seatClass.equals("BC"))
		{
			int t = currentFlight.businessPrice*ticket;
			currentFlight.businessPrice = currentFlight.businessPrice + 200;
			return t;
		}
		else
		{
			int t = currentFlight.economyPrice*ticket;
			currentFlight.economyPrice = currentFlight.economyPrice + 100;
			return t;
		}
	}
	public static void booking(Flight currentFlight,int passengerId,int flightID,String seatClass,int ticket,String meals,int totalcost,int[] numberOfPassengers)
	{
		int mealsPrice;
		if(meals.equals("YES"))
		{
			mealsPrice = 200*ticket;
			totalcost = totalcost + mealsPrice;
		}
		String passenger ;
		passenger = "Passenger ID : "+ passengerId+"\nFlight ID : "+flightID+ "\n" +seatClass+" : "+Arrays.toString(numberOfPassengers)+"\nMeals Requrid : "+meals+"\ntotal Cost : "+ totalcost;
		currentFlight.addPassengerDetails(passenger,totalcost,passengerId,ticket,seatClass,numberOfPassengers);
		System.out.println("-------------------- Booking successfully ----------------");
		
	}
	public static void print(Flight f)
	{
		f.printDetails();
	}
	
	public static boolean cancel(Flight flight,int id)
	{
		boolean f = flight.printfull(id);
		return f;
	}
	
	public static void  lastCancel(Flight currentFlight, int id) 
	{
		currentFlight.find(id);
	}
			
	
		
public static void main(String[] args)
{
    ArrayList<Flight> flights = new ArrayList<>(); 
    flights.add(new Flight(101));
    flights.add(new Flight(102));
	
	int passengerId=1;
   
	Scanner s = new Scanner(System.in);
	
	boolean loop=true;
	while(loop)
	{
		System.out.println("1.booking \n2.cancel \n3.Print Avaiable Seats \n4.print \n5.Exit");
		int choice = s.nextInt();
		switch(choice)
		{
			case 1:
			{
				System.out.println("Enter the Flight ID ");
				int flightID = s.nextInt();
				
				
				if(flightID >= 101 && flightID <= 102)
				{
				  
				}
				else
				{
					 flights.add(new Flight(flightID));
				}
				
				String seatClass = correctSeat(s);
				
				
				Flight currentFlight = null;
				
				for(Flight f : flights)
				{
					if(f.flightIds == flightID)
					{
						currentFlight = f;
						break;
					}
				}
				int ticket = numberOfTickets(currentFlight,s,seatClass);
				int[] numberOfPassengers = arr(currentFlight,ticket,seatClass);
				int totalCost = addTicketSeat(currentFlight,ticket,seatClass);
				String meals = numberOfMeals(s);
				
				booking(currentFlight,passengerId,flightID,seatClass,ticket,meals,totalCost,numberOfPassengers);
				passengerId++;
			}
		    break;
			case 2:
			{
				System.out.println("Enter the PassengerID : ");
				int id  = s.nextInt();
				Flight currentFlight = null;
				for(Flight f : flights)
				{
					if(cancel(f,id))
					{
						currentFlight = f;
						break;
					}
				}
				
				lastCancel(currentFlight, id);
				
				
			}
		    break;
			case 3:
			{
				for(Flight f : flights)
				{
					System.out.println("------------------------------------------------------");
					System.out.println("Flight ID : " + f.flightIds);
					System.out.println("BusinessClass Seat Available : "+ f.businessTicket);
					System.out.println("EconomyClass Seat Available : "+ f.economyTicket);
					System.out.println("------------------------------------------------------");
				}
				
			}
		    break;
			case 4:
			{
				for(Flight f : flights)
				{
					if(f.passengerDetails.size() == 0)
					{
						System.out.println("There is no passenger Available " + f.flightIds);
						
					}
					else
						print(f);
				}
			}
		    break;
			case 5:
			{
				loop=false;
			}
		    break;
		}
	}
	
	
}
}
