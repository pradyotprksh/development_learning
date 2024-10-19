import os
import random
import time
import subprocess
from xml.etree import ElementTree as ET

def get_screen_size():
    result = subprocess.run(['adb', 'shell', 'wm', 'size'], capture_output=True, text=True)
    output = result.stdout.strip()
    dimensions = output.split()[-1]
    width, height = map(int, dimensions.split('x'))
    return width, height

def tap_screen(x, y):
    subprocess.run(['adb', 'shell', 'input', 'tap', str(x), str(y)])

def double_tap_center():
    width, height = get_screen_size()
    center_x = width // 2
    center_y = height // 2
    tap_screen(center_x, center_y)
    time.sleep(0.1)
    tap_screen(center_x, center_y)

def swipe_up():
    os.system("adb shell input swipe 500 1500 500 500")

def main():
    print("Starting to scroll YouTube Shorts...")
    try:
        os.system("killall adb")
        os.system("adb devices")
        while True:
            scroll_interval = random.randint(15, 30)  # Time interval between swipes in seconds
            swipe_up()
            print(f"Swiped up in {scroll_interval} seconds.")
            tap_interval = random.randint(2, scroll_interval - 5)  # Time interval between double tap in seconds
            print(f"Like in {tap_interval} seconds.")
            time.sleep(tap_interval)
            double_tap_center()
            time.sleep(scroll_interval)
    except KeyboardInterrupt:
        print("Stopped scrolling.")

if __name__ == "__main__":
    main()
