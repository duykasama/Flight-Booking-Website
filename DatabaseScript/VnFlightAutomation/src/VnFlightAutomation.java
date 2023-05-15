
import java.util.HashMap;

public class VnFlightAutomation {

    static String[] airlineNames = {"Vietnam Airline", "Vietjet Air", "Jetstar Pacific Airlines", "Bamboo Airways"};
    static String[] airportNames = {"Sân bay quốc tế Nội Bài", "Sân bay QT Tân Sơn Nhất",
        "Sân bay Quốc tế Đà Nẵng", "Sân bay Quốc tế Vân Đồn", "Sân bay Quốc tế Phú Quốc", "Sân bay quốc tế Long Thành",
        "Sân bay Quốc tế Cát Bi", "Sân bay Quốc tế Vinh", "Sân bay Quốc tế Phú Bài", "Sân bay Quốc tế Cam Ranh",
        "Sân bay Quốc tế Liên Khương", "Sân bay Quốc tế Phù Cát", "Sân bay Quốc tế Cần Thơ", "Sân bay Điện Biên Phủ",
        "Sân bay Thọ Xuân", "Sân bay Đồng Hới", "Sân bay Chu Lai", "Sân bay Tuy Hòa", "Sân bay Pleiku", "Sân bay Buôn Mê Thuột",
        "Sân bay Rạch Giá", "Sân bay Cà Mau", "Sân bay Côn Đảo"};
    static int noOfUsers = 8;

    public static void main(String[] args) {
        VnFlightAutomation auto = new VnFlightAutomation();
        System.out.println("use vnflight\n"
                + "GO\n"
                + "insert into [admin] values\n"
                + "('admin','1','','')\n"
                + "insert into [user] values\n"
                + "('hai','1','',''),\n"
                + "('duy','1','',''),\n"
                + "('duc','1','',''),\n"
                + "('huy','1','',''),\n"
                + "('dung','1','',''),\n"
                + "('dat','1','',''),\n"
                + "('phi','1','',''),\n"
                + "('long','1','','')");
        auto.createAirport();
        auto.createFlight(1000);
        auto.createInvoie(100);
        System.out.println("--select id, name, email, phone from [user]\n"
                + "exec pro_checkFlightStatus\n"
                + "\n"
                + "\n"
                + "\n"
                + "USE VNFLIGHT\n"
                + "GO\n"
                + "select fl.id, fl.takeoff_time, fl.landing_time, fl.departure_date, fl.price, fl.airline_name, fl.no_of_seats, ap1.name as 'departure', ap2.name as 'destination', fl.status \n"
                + "                 from flight fl join airport ap1 on fl.departure_id = ap1.id \n"
                + "                join airport ap2 on fl.destination_id = ap2.id");
    }

    public void createAirport() {
        System.out.println("insert into airport values");
        for (int i = 1; i <= airportNames.length; i++) {
            if (i != airportNames.length) {
                System.out.println("(N\'" + airportNames[i - 1] + "\'),");
            } else {
                System.out.println("(N\'" + airportNames[i - 1] + "\')");
            }
        }

    }

    public void createFlight(int numberOfFlights) {
        System.out.println("insert into flight values");
        for (int i = 1; i <= numberOfFlights; i++) {
            String time1 = randTime();
            String time2 = randTime(time1);
            String departureDate = VnFlightAutomation.randInt(1, 12) + "-" + VnFlightAutomation.randInt(1, 28) + "-202" + randInt(0, 5);
            int price = randInt(500000, 5000000, 100000);
            String airlineName = airlineNames[VnFlightAutomation.randInt(0, 3)];
            int noOfSeats = randInt(100, 250, 50);
            int departureId = randInt(1, airportNames.length);
            int destinationId = randIntExcept(1, airportNames.length, departureId);
            if (i != numberOfFlights) {
                System.out.println("(\'" + time1 + "\',\'" + time2 + "\',\'" + departureDate
                        + "\'," + price + ",N\'" + airlineName + "\'," + noOfSeats + ","
                        + departureId + "," + destinationId + ",0),");
            } else {
                System.out.println("(\'" + time1 + "\',\'" + time2 + "\',\'" + departureDate
                        + "\'," + price + ",N\'" + airlineName + "\'," + noOfSeats + ","
                        + departureId + "," + destinationId + ",0)");
            }
        }

    }

    public void createInvoie(int numbeOfInvoices) {
        System.out.println("insert into invoice values");
        for (int i = 1; i <= numbeOfInvoices; i++) {
            String bookingDate = VnFlightAutomation.randInt(1, 12) + "-" + VnFlightAutomation.randInt(1, 28) + "-202" + randInt(0, 5);
            if (i != numbeOfInvoices) {
                System.out.println("(" + randInt(1, noOfUsers) + "," + randInt(1, 1000) + ",\'" + bookingDate
                        + "\'," + randInt(1000000, 5000000, 100000) + "," + randInt(0, 1) + "),");
            } else {
                System.out.println("(" + randInt(1, noOfUsers) + "," + randInt(1, 1000) + ",\'" + bookingDate
                        + "\'," + randInt(1000000, 5000000, 100000) + "," + randInt(0, 1) + ")");
            }
        }
    }

    public static int randInt(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static int randInt(int min, int max, int unit) {
        return Math.round(VnFlightAutomation.randInt(min, max) / unit) * unit;
    }

    public static int randIntExcept(int min, int max, int except) {
        int output = randInt(min, max);
        return (output != except) ? output : randIntExcept(min, max, except);
    }

    public static String randTime() {
        String min = String.valueOf(randInt(0, 59, 5));
        String hour = String.valueOf(randInt(0, 23));
        min = (min.equals("0") ? "00" : min);
        hour = (hour.equals("0") ? "00" : hour);
        return hour + ":" + min + ":00";
    }

    public static String randTime(String time) {
        String[] str = time.split(":");
        String hour = String.valueOf((Integer.parseInt(str[0]) + randInt(2, 6)) % 24);
        String min = String.valueOf((Integer.parseInt(str[1]) + randInt(0, 61, 5)) % 60);
        hour = (hour.equals("0") ? "00" : hour);
        min = (min.equals("0") ? "00" : min);

        return hour + ":" + min + ":00";
    }
}
