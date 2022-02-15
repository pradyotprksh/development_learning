"""PyBlog"""


import tkinter as tk
from tkinter import ttk
from src import Constants


if __name__ == '__main__':
    root = tk.Tk()
    root.title(Constants.Variables.PROJECT_NAME)

    screen_width = root.winfo_screenwidth()
    screen_height = root.winfo_screenheight()

    # Full Screen
    root.geometry(f"{screen_width}x{screen_height}+{0}+{0}")
    # TODO: Project icon doesn't show
    root.iconbitmap(Constants.Paths.MAIN_ICON)

    ttk.Label(master=root, text="PyBlog").pack()
    root.mainloop()
