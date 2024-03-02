import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Function;

	public class Manager {
		 private final static Scanner sc = new Scanner(System.in);

		    public static int checkInputIntLimit(int min, int max) {
		        while (true) {
		            try {
		                int result = Integer.parseInt(sc.nextLine().trim());
		                if (result < min || result > max) {
		                    throw new NumberFormatException();
		                }
		                return result;
		            } catch (NumberFormatException e) {
		                System.err.println("Please input number in range [" + min + ", " + max + "]");
		                System.out.print("Enter again: ");
		            }
		        }
		        
		    }
	
	 public static String checkString() {
	        while (true) {
	            String result = sc.nextLine().trim();
	            if (result.isEmpty()) {
	                System.out.print("Not empty. Enter again: ");
	            } else {
	                return result;
	            }
	        }
	    }
	 
	 public static boolean checkDateFormat(String date) {
	        SimpleDateFormat sdfrmt = new SimpleDateFormat("d/M/yy");
	        sdfrmt.setLenient(false);
	        try {
	            Date javaDate = sdfrmt.parse(date);
	        } catch (ParseException e) {
	            return false;
	        }
	        return true;
	    }
	
	
	public static boolean checkContinue(String... str) {
        System.out.println("====================================================");
        if (str.length == 0) System.out.println("Do you want to continue(Y/N)? ");
        else System.out.println(str[0]);
        while (true) {
            String result = checkString();
            if (result.compareToIgnoreCase("Y") == 0) {
                return true;
            } else if (result.compareToIgnoreCase("N") == 0) {
                return false;
            } else {
                System.out.println("Please enter Y or N.");
            }
        }
	  }
	
	
    private static void reEnter(String s) {
        System.out.print(s);
        System.out.print(" Please enter again: ");
    }
	
    public static Boolean isNotEmpty(String temp) {
        return (!temp.isEmpty());
    }

    public static Boolean checkEmailFormat(String email) {
        return (email.matches("^(.+)@(.+)$"));
    }

    public static Boolean checkPhoneNumberFormat(String phoneNumber) {
        try {
            long temp = Long.parseLong(phoneNumber);
            return (9 <= phoneNumber.length() && phoneNumber.length() <= 12);
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
	
	public static String check(String welcomeString, String errString, Function<String, Boolean> function) {
        System.out.print(welcomeString);
        String result = checkString();
        while (function.apply(result) == false) {
            reEnter(errString);
            result = checkString();
        }
        return result;
    }

    public static String updateInfo(String it, String welcomeString, String errString, Function<String, Boolean> function) {
        System.out.print(welcomeString);
        String result = sc.nextLine().trim();
        if (result.isEmpty()) return it;
        while (function.apply(result) == false) {
            reEnter(errString);
            result = checkString();
        }
        return result;
    }

    public static <T extends ID> T checkIdExist(Date id, ArrayList<T> lis) {
        T result = null;
        for (T it : lis) {
            if (it.getId().compareTo(id) == 0) {
                result = it;
                break;
            }
        }
        return result;
    }

    public static <T extends ID> String checkId(String welcomeString, ArrayList<T> lis) {
        System.out.print(welcomeString);
        String id = checkString();
        while (checkId(id, lis) != null) {
            reEnter("ID exists.");
            id = checkString();
        }
        return id;
    }

    public static Boolean checkPositiveNumber(String number) {
        try {
            int temp = Integer.parseInt(number);
            return (temp > 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static <T> void checkRecentlyAdded(T temp, ArrayList<T> lis, String str) {
        System.out.println("Checking...");
        boolean flag = false;
        for (T it : lis) {
            if (it == temp) flag = true;
        }
        if (flag) System.out.format("Successfully add %s.\n", str);
        else System.out.format("Failed to add %s.\n", str);
    }

    public static Grade findGradeExist(ArrayList<Grade> lis, Date studentId, Date subjectId) {
        Grade result = null;
        for (Grade it : lis) {
            if (((ID) it.student).getId().compareTo(studentId) == 0 && ((ID) it.subject).getId().compareTo(subjectId) == 0) {
                result = it;
                break;
            }
        }
        return result;
    }

    public static Boolean checkDouble(String num) {
        try {
            double temp = Double.parseDouble(num);
            return (temp > 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static <T extends ID> T findExist(ArrayList<T> lis, String... str) {
        String welcomeString = String.format("Enter %s ID: ", str[0]);
        String id = Manager.check(welcomeString, "Not empty.", Manager::isNotEmpty);
        T pointer = (T) Manager.checkIdExist(id, lis);
        if (pointer == null) {
            System.out.format("%s ID does not exist.\n", str[0]);
            return null;
        }
        return pointer;
    }
    public static double getAverageMark(String t1, String t2, String t3) {
        double lab = Double.parseDouble(t1);
        double progressTest = Double.parseDouble(t2);
        double finalExam = Double.parseDouble(t3);
        double averageMark = 0.3 * lab + 0.3 * progressTest + 0.4 * finalExam;
        averageMark *= 100;
        averageMark = Double.valueOf(new Integer((int) averageMark));
        averageMark = averageMark / 100;
        return averageMark;
    }
    }
