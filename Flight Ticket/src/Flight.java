import java.util.*;



public class Flight {

	
	int flightIds ;
	
	int businessTicket;
	int economyTicket;
	
	int businessPrice;
	int economyPrice;
	int foodPrice;
	
	ArrayList<String> passengerDetails;
	ArrayList<Integer> passengerID;
	ArrayList<Integer>  bookedTicketPerPassenger;
	ArrayList<Integer> seatNumber;
	ArrayList<String> ticketSeats;
	
	
	List<Integer> businessTicketPositions;
	List<Integer> economyTicketPositions ;
	static Map<Integer,String> passengers ;
	
	
	public Flight(int id)
	{
		flightIds = id;
		
		businessTicket = 6;
		economyTicket =12;
		
		businessPrice  = 2000;
		economyPrice = 1000;
		foodPrice = 200;
		
		passengerDetails = new ArrayList<>();
		passengerID = new ArrayList<>();
		bookedTicketPerPassenger = new ArrayList<>();
		
		ticketSeats = new ArrayList<>();
		seatNumber = new ArrayList<>();
		businessTicketPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
		economyTicketPositions = new ArrayList<>(Arrays.asList(7,8,9,10,11,12,13,14,15,16,17,18));
		passengers = new HashMap<>();
		
	}
	


	public void addPassengerDetails(String passenger,int totalcost, int passengerId,int ticket,String seat,int[] numberOfPassengers)
	{
		passengerDetails.add(passenger);
		passengerID.add(passengerId);
		bookedTicketPerPassenger.add(ticket);
		ticketSeats.add(seat);
		
		seatNumber.add(numberOfPassengers[0]);
		
		passengers.put(passengerId, passenger);
		
	}
	
	public void printDetails()
	{
		//System.out.println("Flight Id "+ flightIds);
		for(String s : passengerDetails)
		{
			System.out.println("------------------------------------------------------");
			System.out.println(s);
			System.out.println("------------------------------------------------------");
		}
	}

	public boolean printfull(int id)
    {
        if(passengers.size() == 0)
        {
            System.out.println("No details of passengers");
            return false;
        }
        Set s1 = passengers.entrySet();
		
		Iterator itr = s1.iterator();
		while(itr.hasNext())
		{
			Map.Entry m1 = (Map.Entry)itr.next();
			if(m1.getKey().equals(id))
			{
				return true;
			}
		}
		return false;
    }
	
	
	public void find(int id)
	{
		int indexOf = passengerID.indexOf(id);
		if(indexOf < 0)
		{
			System.out.println("print invaild ");
			return;
		}
		
		int ticketToCancel = bookedTicketPerPassenger.get(indexOf);
	    String  seatToCancel = ticketSeats.get(indexOf);
		int firstNumberOfTicket = seatNumber.get(indexOf);
	    if(seatToCancel.equals("BC"))
	    {
	    	businessTicket += ticketToCancel;
	    	for(int i=0;i< ticketToCancel; i++)
	    	{
	    		businessTicketPositions.add(firstNumberOfTicket++);
	    	}
	    }
	    else
	    {
	    	economyTicket += ticketToCancel;
	    	for(int i=0;i< ticketToCancel; i++)
	    	{
	    		economyTicketPositions.add(firstNumberOfTicket++);
	    	}
	    }
	    passengerDetails.remove(indexOf);
		passengerID.remove(indexOf);
		bookedTicketPerPassenger.remove(indexOf);
		
		System.out.println("cancel Amount : " + 200*ticketToCancel);
		
		System.out.println("-------------------Cancel The Ticket----------------");
		
		
		
	}
	
	
	
}
