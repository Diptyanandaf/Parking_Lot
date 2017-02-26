import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	/**
	 * created by DIPTYANANDA FAKHRIANTO
	 *  
	 */
	private static String[] carid;
	private static String[] color;
	private static ArrayList<String> regcolor;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("parking_lot file_inputs.txt"));
		try {
	        String line = br.readLine();
	        Integer nlot = 0;
	        carid = null;
	        color = null;
	        while (line != null) {
	        	String[] spline = null;
	        	spline = line.split(" ");
	        	String command = spline[0];
	        	if (command.equals("create_parking_lot")) {
	        		if (carid == null && color == null) {
	        			nlot = Integer.parseInt(spline[1]);
		        		carid = createLot(nlot);
		        		color = createLot(nlot);
		        		System.out.println("Created a parking lot with "+nlot+" slots");
	        		} else {
	        			System.out.println("Parking lot already created");
	        		}
	        	} else if (command.equals("park")) {
	        		if (carid == null && color == null) {
	        			System.out.println("Parking lot not created");
	        		} else {
	        		
	        			int setlot = checkLot(nlot);
	        			if (setlot < 0) {
	        				System.out.println("Sorry, parking lot is full");
	        			} else {
	        				carid[setlot] = spline[1];
	        				color[setlot] = spline[2];
	        				System.out.println("Allocated slot number: "+(setlot+1));
	        			}
	        			
	        		}
	        	} else if (command.equals("leave")) {
	        		if (carid == null && color == null) {
	        			System.out.println("Parking lot not created");
	        		} else {
	        			int leavelot = Integer.parseInt(spline[1]);
	        			if (carid[leavelot-1] == "" && color[leavelot-1] == "") {
	        				System.out.println("Slot number "+leavelot+" is already free");
	        			} else {
	        				carid[leavelot-1] = "";
	        				color[leavelot-1] = "";
	        				System.out.println("Slot number "+leavelot+" is free");
	        			}
	        		}
	        	} else if (command.equals("status")) {
	        		if (carid == null && color == null) {
	        			System.out.println("Parking lot not created");
	        		} else {
	        			System.out.println("No.	Registrasion Slot No.	Color");
	        			for (int i=0; i<nlot; i++) {
	        				if (!carid[i].isEmpty() && !color[i].isEmpty()) {
	        					System.out.println((i+1)+"\t"+carid[i]+"\t\t"+color[i]);
	        				}
	        			}
	        		}
	        	} else if (command.equals("registration_numbers_for_cars_with_colour")) {
	        		String colorreg = spline[1];
	        		String regcolor_fix = colorReg(1, colorreg, nlot);
	        		if (!regcolor_fix.equals("")) {
	        			System.out.println(regcolor_fix);
	        		} else {
	        			System.out.println("Not found");
	        		}
	        	} else if (command.equals("slot_numbers_for_cars_with_colour")) {
	        		String colorslot = spline[1];
	        		String regcolor_fix = colorReg(2, colorslot, nlot);
	        		if (!regcolor_fix.equals("")) {
	        			System.out.println(regcolor_fix);
	        		} else {
	        			System.out.println("Not found");
	        		}
	        	} else if (command.equals("slot_number_for_registration_number")) {
	        		String numberslot = spline[1];
	        		String slotnumber = numberSlot(numberslot, nlot);
	        		if (!slotnumber.equals("")) {
	        			System.out.println(slotnumber);
	        		} else {
	        			System.out.println("Not found");
	        		}
	        	}
	            line = br.readLine();
	        }
		} finally {
			br.close();
		}
	}

	private static String[] createLot(Integer nlot) {
		// TODO Auto-generated method stub
		String[] arraylot;
		arraylot = new String[nlot];
		for (int i=0; i<nlot; i++) {
			arraylot[i] = "";
		}
		return arraylot;
	}
	
	private static Integer checkLot(Integer nlot) {
		// TODO Auto-generated method stub
		int lotnum = -1;
		for (int i=0; i<nlot; i++) {
			if (carid[i].isEmpty() && color[i].isEmpty()) {
				lotnum = i;
				break;
			}
		}
		return lotnum;
	}
	
	private static String colorReg(Integer param, String colorreg, Integer nlot) {
		// TODO Auto-generated method stub
		regcolor = new ArrayList<String>();
		String regcolor_str;
		if (param == 1) {
			for (int i=0; i<nlot; i++) {
				if (color[i].toUpperCase().equals(colorreg.toUpperCase())) {
					regcolor.add(carid[i]);
				}
			}
			regcolor_str = String.valueOf(regcolor);
		} else {
			for (int i=0; i<nlot; i++) {
				if (color[i].toUpperCase().equals(colorreg.toUpperCase())) {
					regcolor.add(String.valueOf(i+1));
				}
			}
			regcolor_str = String.valueOf(regcolor);
		}
		String regcolor_set = regcolor_str.replaceAll("[^\\w\\s\\,-]", "");	
		return regcolor_set;
	}
	
	private static String numberSlot(String numberslot, Integer nlot) {
		// TODO Auto-generated method stub
		String slotnumber_set = "";
		for (int i=0; i<nlot; i++) {
			if (carid[i].equals(numberslot)) {
				slotnumber_set = String.valueOf(i+1);
			}
		}
		return slotnumber_set;
	}
}
