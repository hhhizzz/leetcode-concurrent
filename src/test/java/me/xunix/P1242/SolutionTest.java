package me.xunix.P1242;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

public class SolutionTest {

  public static void main(String[] args) {
    Solution solution = new Solution();
    String hostname = solution.getHostname("https://news.yfs.123.ahoo.com:8281/news/topics/");
    System.out.println(hostname);

    List<String> result = solution.crawl("http://news.yahoo.com/news/topics/", url -> {
      List<String> urls = new LinkedList<>();
      if (url.equals("http://news.yahoo.com/news/topics/")) {//2
        urls.add("http://news.yahoo.com");
        urls.add("http://news.yahoo.com/news");
        urls.add("http://news.google.com");
      } else
        if (url.equals("http://news.google.com")) { //3
        urls.add("http://news.yahoo.com/news/topics/");
        urls.add("http://news.yahoo.com/news/topics/");
      } else if (url.equals("http://news.yahoo.com")) {//0
        urls.add("http://news.yahoo.com/us");
        urls.add("http://news.yahoo.com/news/topics/");
      } else if (url.equals("http://news.yahoo.com/news")) {//1
        urls.add("http://news.yahoo.com/news/topics/");
        urls.add("http://news.google.com");
      } else if (url.equals("http://news.yahoo.com/us")) {
        urls.add("http://news.yahoo.com");
      }
      return urls;
    });

    System.out.println(result);

  }
}
