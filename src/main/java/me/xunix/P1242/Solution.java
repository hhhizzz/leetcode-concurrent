package me.xunix.P1242;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface HtmlParser {
  public List<String> getUrls(String url);
}

//这道题好像不让用forkJoinPool Executor之类的工具 那就用最原始的工具，手动启动线程
class Solution {
  private final Set<String> crawled = new HashSet<>();
  private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
  private String hostname;
  private Pattern hostnameRegex = Pattern.compile("https?:\\/\\/([\\w\\.\\-\\d]+)(:\\d+)?");

  String getHostname(String url) {
    Matcher matcher = hostnameRegex.matcher(url);
    matcher.find();
    return matcher.group(1);
  }

  public List<String> crawl(String startUrl, HtmlParser htmlParser) {
    this.hostname = getHostname(startUrl);
    LinkedList<String> result = new LinkedList<>();
    crawled.add(startUrl);
    result.add(startUrl);

    List<String> urls = htmlParser.getUrls(startUrl);
    if (urls != null) {
      for (String url : urls) {
        if (!crawled.contains(url) && getHostname(url).equals(this.hostname)) {
          crawled.add(url);
          queue.offer(url);
          result.add(url);
        }
      }
    }

    AtomicInteger runningTask = new AtomicInteger(queue.size());

    while (true) {
      try {
        String current = queue.poll(15, TimeUnit.MILLISECONDS);
        if (current != null) {
          new Thread(() -> {
            List<String> nextUrls = htmlParser.getUrls(current);
            if (nextUrls != null) {
              synchronized (crawled) {
                for (String url : nextUrls) {
                  if (!crawled.contains(url) && getHostname(url).equals(this.hostname)) {
                    crawled.add(url);
                    result.add(url);
                    queue.offer(url);
                    runningTask.getAndIncrement();
                  }
                }
              }
            }
            runningTask.getAndDecrement();
          }).start();
        } else {
          if (runningTask.get() == 0) {
            break;
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
        return null;
      }
    }
    return result;
  }
}

