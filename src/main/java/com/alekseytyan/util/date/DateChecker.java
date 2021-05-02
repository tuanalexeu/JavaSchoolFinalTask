package com.alekseytyan.util.date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Class contains information about dates of order and time needed to complete the order
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DateChecker {

    private LocalDateTime start;
    private LocalDateTime end;
    private int hours;

    /**
     * Method calculates hours the drivers are going to spend in duty
     * @param routeTime - actual time needed to complete the order
     * @return - DateChecker object which contains start & end dates & needed time
     */
    public static DateChecker calculateHoursInMonth(int routeTime) {

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(routeTime);
        long neededHours;

        if(start.getMonthValue() == end.getMonthValue()) {
            neededHours = ChronoUnit.HOURS.between(start, end);
        } else {
            LocalDateTime endOfMonth = LocalDateTime.of(
                    start.getYear(),
                    start.getMonth().plus(1),
                    1,
                    0, 0, 0);

            neededHours = ChronoUnit.HOURS.between(start, endOfMonth);
        }

        DateChecker dateChecker = new DateChecker();
        dateChecker.setHours((int) neededHours);
        dateChecker.setStart(start);
        dateChecker.setEnd(end);

        return dateChecker;
    }
}
