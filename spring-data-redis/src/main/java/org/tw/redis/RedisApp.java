package org.tw.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableScheduling
public class RedisApp implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class, args);
    }

    @Override
    public void run(String... args) {

    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void testWriteAndRead() {
        stringRedisTemplate.boundValueOps("6666666666").set("66661234567890", 10, TimeUnit.MINUTES);
        System.out.println(stringRedisTemplate.boundValueOps("6666666666").get());

    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void testRead() {

        System.out.println(stringRedisTemplate.boundValueOps("3").get());
    }


    public static BigDecimal test(String handicap, String market, BigDecimal homeOdds,
                                  BigDecimal awayOdds, BigDecimal drawOdds, Integer homeScore, Integer awayScore, String option, BigDecimal amount) throws Exception {

        BigDecimal homeScoreBigDecimal = BigDecimal.valueOf(homeScore);
        BigDecimal awayScoreBigDecimal = BigDecimal.valueOf(awayScore);
        BigDecimal subtractScore = null;
        BigDecimal odds = null;
        BigDecimal reward = new BigDecimal(0);
        switch (market) {
            case "1_21"://足球欧洲让分
                homeScoreBigDecimal = homeScoreBigDecimal.add(new BigDecimal(handicap));
            case "1_1"://足球胜负平
                subtractScore = homeScoreBigDecimal.subtract(awayScoreBigDecimal);
                if (subtractScore.equals(BigDecimal.ZERO)) {
                    if (option.equals("draw")) {
                        odds = drawOdds;
                    }
                }
                if (subtractScore.compareTo(BigDecimal.ZERO) > 0) {
                    if (option.equals("home")) {
                        odds = homeOdds;
                    }

                }
                if (subtractScore.compareTo(BigDecimal.ZERO) < 0) {
                    if (option.equals("away")) {
                        odds = awayOdds;
                    }
                }
                if (odds != null) {
                    reward = amount.multiply(odds);
                }
                break;
            case "1_2"://亚盘

                List<BigDecimal> handicapList = Arrays.stream(handicap.split(","))
                        .map(p -> new BigDecimal(p))
                        .collect(Collectors.toList());
                if (handicapList.size() > 2 || handicapList.size() < 1) {
                    throw new Exception("error");
                }
                if (handicapList.size() == 1) {
                    homeScoreBigDecimal = homeScoreBigDecimal.add(handicapList.get(0));
                    subtractScore = homeScoreBigDecimal.subtract(awayScoreBigDecimal);

                    if (subtractScore.equals(BigDecimal.ZERO)) {
                        return amount;
                    }
                    if (subtractScore.compareTo(BigDecimal.ZERO) > 0) {
                        if (option.equals("home")) {
                            odds = homeOdds;
                        }
                    }
                    if (subtractScore.compareTo(BigDecimal.ZERO) < 0) {
                        if (option.equals("away")) {
                            odds = awayOdds;
                        }
                    }
                    if (odds == null) {
                        return BigDecimal.valueOf(0);
                    }
                    return amount.multiply(odds);
                }

                if (handicapList.size() == 2) {
                    for (BigDecimal handicapBigDecimal : handicapList) {
                        odds = null;
                        BigDecimal currentHomeScoreBigDecimal = homeScoreBigDecimal.add(handicapBigDecimal);
                        subtractScore = currentHomeScoreBigDecimal.subtract(awayScoreBigDecimal);

                        if (subtractScore.equals(BigDecimal.ZERO)) {
                            reward = reward.add(amount.divide(new BigDecimal(2)));
                        }
                        if (subtractScore.compareTo(BigDecimal.ZERO) > 0) {
                            if (option.equals("home")) {
                                odds = homeOdds;
                            }
                        }
                        if (subtractScore.compareTo(BigDecimal.ZERO) < 0) {
                            if (option.equals("away")) {
                                odds = awayOdds;
                            }
                        }
                        if (odds != null) {
                            reward = reward.add(amount.divide(new BigDecimal(2)).multiply(odds));
                        }
                    }
                }
                break;
            default:
                throw new Exception("error");
        }
        return reward;
    }
}



