import datetime
from datetime import datetime as dt
from src import log_debug


def datetime_module():
    """
    Let's take a look on date time module for python
    :return: None
    """

    my_time = datetime.time(2, 20)
    log_debug(my_time.minute)
    log_debug(my_time.hour)
    log_debug(my_time)
    log_debug(my_time.microsecond)
    log_debug(type(my_time))

    today = datetime.date.today()
    log_debug(today)
    log_debug(today.year)
    log_debug(today.month)
    log_debug(today.day)
    log_debug(today.ctime())

    my_date_time = dt(2021, 2, 12, 14, 45, 30, 800)
    log_debug(my_date_time)
    my_date_time = my_date_time.replace(year=2022)
    log_debug(my_date_time)

    date1 = datetime.date(2022, 9, 3)
    date2 = datetime.date(2021, 5, 7)
    diff = date1 - date2
    log_debug(diff.days)

    date1 = datetime.datetime(2022, 9, 3, 22, 0)
    date2 = datetime.datetime(2021, 9, 3, 12, 0)
    diff = date1 - date2
    log_debug(diff)
    log_debug(diff.seconds)
    log_debug(diff.total_seconds())
