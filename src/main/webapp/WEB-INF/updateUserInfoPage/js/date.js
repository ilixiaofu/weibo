$(document).ready(function () {
    var i = -1;
    // 添加年份，从1910年开始
    for (i = 1910; i <= new Date().getFullYear(); i++) {
        addOption(birth_year, i, i - 1909);
        /*// 默认选中1988年
        if (i == 1988) {
            birth_year.options[i-1910].selected = true;
        }*/
    }
    // 添加月份
    for (i = 1; i <= 12; i++) {
        addOption(birth_month, i, i);
    }
    // 添加天份，先默认31天
    for (i = 1; i <= 31; i++) {
        addOption(birth_day, i, i);
    }


    //$("#birth_month"). birth_year  birth_day
});


// 设置每个月份的天数
function setDays(year, month, day) {
    var monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var yea = year.options[year.selectedIndex].text;
    var mon = month.options[month.selectedIndex].text;
    var num = monthDays[mon - 1];
    if (mon == 2 && isLeapYear(yea)) {
        num++;
    }
    for (var j = day.options.length - 1; j >= num; j--) {
        day.remove(j);
    }
    for (var i = day.options.length; i <= num; i++) {
        addOption(birth_day, i, i);
    }
}

// 判断是否闰年
function isLeapYear(year) {
    return (year % 4 == 0 || (year % 100 == 0 && year % 400 == 0));
}

// 向select尾部添加option
function addOption(selectbox, text, value) {
    var option = document.createElement("option");
    option.text = text;
    option.value = value;
    selectbox.options.add(option);
}