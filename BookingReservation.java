import java.util.*;

public class BookingReservation {
    static class buildings {
        ArrayList<String> buildings = new ArrayList<String>();

        public boolean hasBuilding(String building) {
            if (buildings.contains(building)) {
                return true;
            } else {
                return false;
            }
        }

        void addBuilding(String building) {
            if (hasBuilding(building)) {
                System.out.println("Building already exists");
                return;
            }
            buildings.add(building);
            System.out.println("Building " + building + " added to system");
        }

        void printBuildings() {
            for (String building : buildings) {
                System.out.println(building);
            }
        }
    }

    static class floors extends buildings {
        HashMap<String, List<String>> floors = new HashMap<String, List<String>>();

        boolean hasFloors(String building, String floor) {
            if (!hasBuilding(building)) {
                System.out.println("Building " + building + "is not present! Check again");
                return false;
            }
            List<String> floorList = floors.get(building);
            if (floorList.contains(floor)) {
                return true;
            }
            return false;
        }

        void addFloors(String building, String floor) {
            if (building == "" || floor == "") {
                System.out.println("Dont put blank!");
            }
            if (!hasBuilding(building)) {
                System.out.println("Building " + building + " doesn't exist");
                return;
            }
            List<String> tempFloor = new ArrayList<String>();
            if (floors.get(building) == null) {
                floors.put(building, tempFloor);
                System.out.println("Floor " + floor + " added in building " + building);
                return;
            }
            tempFloor = floors.get(building);
            if (tempFloor.contains(floor)) {
                System.out.println("Floor Already present");
                return;
            } else {
                tempFloor.add(floor);
                floors.put(building, tempFloor);
            }
            System.out.println("Floor " + floor + " added in building " + building);
        }
    }

    static class rooms extends floors {
        HashMap<String, List<String>> rooms = new HashMap<>();

        boolean hasRoom(String building, String floor, String room) {
            if (!hasFloors(building, floor)) {
                System.out.println("Such Place doesn't exist");
                return false;
            }
            String roomKeys = building + ":" + floor;
            if (!rooms.containsKey(roomKeys)) {
                return false;
            }
            if (!rooms.get(roomKeys).contains(room)) {
                return false;
            }
            return true;
        }

        void addRoom(String building, String floor, String room) {
            if (!hasRoom(building, floor, room)) {
                List<String> roomListOnThisFloor = new ArrayList<>();
                String roomKeys = building + ":" + floor;

                roomListOnThisFloor = rooms.get(roomKeys);
                roomListOnThisFloor.add(room);
            } else {
                System.out.println("Error in entring room... Room already present");
            }
        }

    }

    static class Reservation extends rooms {
        HashMap<String, List<Integer>> reservations = new HashMap<>();

        void addReservation(String building, String floor, String room, int startTime, int endTime) {
            if (!hasRoom(building, floor, room)) {
                System.out.println("Missing place.. check againn");
                return;
            }

            String reservationKey = building + ":" + floor + ":" + rooms;
            if (reservations.get(reservationKey).contains(startTime)
                    || reservations.get(reservationKey).contains(endTime)) {
                System.out.println("Reservation is already present \n Cant book");
                return;
            }

            List<Integer> timingsBookedForRoom = reservations.get(reservationKey);
            for (int iterator = startTime; iterator <= endTime; iterator++) {
                timingsBookedForRoom.add(iterator);
            }
            reservations.put(reservationKey, timingsBookedForRoom);
            System.out.println("Rooms Booked Successfully");

        }

        void cancelReservation(String building, String floor, String room, int startTime, int endTime) {
            if (!hasRoom(building, floor, room)) {
                System.out.println("Missing place.. No such reservation check againn");
                return;
            }

            String reservationKey = building + ":" + floor + ":" + rooms;
            if (!reservations.get(reservationKey).contains(startTime)
                    || !reservations.get(reservationKey).contains(endTime)) {
                System.out.println("No Reservation found \n Cant Cancel, get lost noob");
                return;
            }

            List<Integer> timingsBookedForRoom = reservations.get(reservationKey);
            for (int iterator = startTime; iterator <= endTime; iterator++) {
                timingsBookedForRoom.add(iterator);
            }
            reservations.put(reservationKey, timingsBookedForRoom);
            System.out.println("Rooms Booked Successfully");

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        rooms rooms = new rooms();
        System.out.println(
                "********************************Welcome to BookingReservation********************************");
        System.out.println("Enter your command/ Type EXIT(in caps) to exit");
        command = sc.nextLine();
        while (!command.equals("EXIT")) {
            String[] commandSplitter = command.split(" ");
            try {
                switch (commandSplitter[0]) {
                    case "ADD":
                        switch (commandSplitter[1]) {
                            case "BUILDING":
                                rooms.addBuilding(commandSplitter[2]);
                                break;
                            case "FLOOR":
                                rooms.addFloors(commandSplitter[2], commandSplitter[3]);
                                break;
                            case "CONFROOM":
                                System.out.println("Adding " + commandSplitter[4] + " to " + commandSplitter[2]
                                        + " at floor " + commandSplitter[3]);
                                rooms.addRoom(commandSplitter[2], commandSplitter[3], commandSplitter[4]);
                                break;
                            default:
                                System.out.println("Wrong selection try again");
                        }
                    case "LIST":

                        break;

                    case "BOOK":
                        break;

                    case "CANCEL":
                        break;

                    default:
                        System.out.println("Wrong choice try again");

                }
            } catch (Exception e) {
                System.out.println("Please check command and try again. Error: " + e);
            }
            command = sc.nextLine();
        }
        System.out.println(
                "*********************Thanks for choosing our services Have a great time!***********************************");
        sc.close();
    }
}
