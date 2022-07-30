# A basic scheduling problem
#
# Assumption:
# 1. Airport with a single runway.
#
# Will be used for reservations for future landings
#
# Reserve requests specified landings time T
#
# Add T to the set R of landing times if no other landings are scheduled within K minutes,
# and K is a parameter could vary (K will depend on the weather condition or any other factor)
#
# Everytime a plane is already landed, then take the particular time from the set R
#
# Create a solution with O(log n) time complexity.
#
# For example:
# Current set R looks like below
#       41.2  49  56.3
# --|----|----|----|---->
#  Now
#  37
#
# And K is 3
#
# So,
# 53 -> Ok to add in the set
# 44 -> Not allowed since its close to 41.2
# 20 -> Not allowed because its pastime


class Node:
    def __init__(self, key):
        self.key = key
        self.parent = None
        self.left = None
        self.right = None
        self.sub_tree_size = 1

    def __str__(self) -> str:
        if self.left is None and self.right is None:
            return f"{self.key}({self.sub_tree_size})"
        elif self.left is None:
            return f"{self.key}({self.sub_tree_size})-->[{self.right}]"
        elif self.right is None:
            return f"[{self.left}]<--{self.key}({self.sub_tree_size})"
        else:
            return f"[{self.left}]<--{self.key}({self.sub_tree_size})-->[{self.right}]"


class RunwayReservationSystem:
    def __init__(self, k=3):
        self.k = k
        self.current_time = 15
        self.root = None

    def add_new_flight_land_time(self, time):
        if time < self.current_time:
            print("Land time is less than the current time, might be for the next day")
            return
        new_time = Node(key=time)
        if self.root is None:
            self.root = new_time
        else:
            last_node = self.root
            while True:
                if abs(last_node.key - new_time.key) < self.k:
                    print(f"Time difference between the previous landing {last_node.key} is "
                          f"less than {self.k} for the given time {new_time.key}")
                    break
                if last_node.key > new_time.key:
                    if last_node.left is None:
                        new_time.parent = last_node
                        last_node.left = new_time

                        update_size_pnt = new_time.parent
                        while True:
                            update_size_pnt.sub_tree_size += 1
                            update_size_pnt = update_size_pnt.parent
                            if update_size_pnt is None:
                                break
                        break
                    else:
                        last_node = last_node.left
                else:
                    if last_node.right is None:
                        new_time.parent = last_node
                        last_node.right = new_time

                        update_size_pnt = new_time.parent
                        while True:
                            update_size_pnt.sub_tree_size += 1
                            update_size_pnt = update_size_pnt.parent
                            if update_size_pnt is None:
                                break
                        break
                    else:
                        last_node = last_node.right

    def number_of_planes_going_to_land(self, time):
        temp = self.root
        number_of_planes = 0
        while True:
            if temp is None:
                break
            if temp.key >= time:
                number_of_planes += 1
            if temp.key < time:
                number_of_planes += 1
                if temp.left is not None:
                    number_of_planes += temp.left.sub_tree_size
                temp = temp.right
            else:
                temp = temp.left
        return number_of_planes

    def print_current_reservations(self):
        print(f"{self.root}")


def start_reservation_process():
    runway_reservation_system = RunwayReservationSystem()
    for item in [48, 45, 42, 84, 74, 100, 71, 82, 92, 105]:
        runway_reservation_system.add_new_flight_land_time(item)
        runway_reservation_system.print_current_reservations()
    print(runway_reservation_system.number_of_planes_going_to_land(105))

    # Example with user input
    # user_selection = 1
    # while user_selection in [1, 2]:
    #     user_selection = src.UserInput.get_user_selection(
    #         "Please select an option"
    #         "\n1. Add flight time"
    #         "\n2. Show current reservations"
    #     )
    #     if user_selection == 1:
    #         key_user_input = src.UserInput.get_user_selection(
    #             "Please enter the flight land time"
    #         )
    #         runway_reservation_system.add_new_flight_land_time(key_user_input)
    #     runway_reservation_system.print_current_reservations()
