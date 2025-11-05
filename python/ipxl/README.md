## This is something where I try to reverse engineer the android app that runs the LED signs. And this is where I take notes on things I find

Mac-addr for my led sign: 'C9:51:78:CE:54:3D'

clock has modes 1 through 8

For clock mode reverse following:
        int year = DateUtils.getYear() - 2000;
        int month = DateUtils.getMonth();
        int day = DateUtils.getDay();
        int weekOfDate = DateUtils.getWeekOfDate(new Date());