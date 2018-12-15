package com.zda.arithmetic;

/**
 * 贪婪算法 仅是在某种意义上的局部最优解
 * 贪心算法，又称贪婪算法(Greedy Algorithm)，是指在对问题求解时，总是做出在当前看来是最好的选择。也就是说，不从整体最优解出发来考虑，它所做出的仅是在某种意义上的局部最优解。
 * 贪婪算法是一种分阶段的工作，在每一个阶段，可以认为所做决定是最好的，而不考虑将来的后果。这种“眼下能够拿到的就拿”的策略是这类算法名称的来源。
 * 贪心算法没有固定的算法框架，算法设计的关键是贪心策略的选择。必须注意的是，贪心算法不是对所有问题都能得到整体最优解，选择的贪心策略必须具备无后效性，即某个状态以后的过程不会影响以前的状态，只与当前状态有关。所以对所采用的贪心策略一定要仔细分析其是否满足无后效性。
 */
public class Greedy {
    /**
     * 因为用贪心算法只能通过解局部最优解的策略来达到全局最优解，因此，
     *
     * 一定要注意判断问题是否适合采用贪心算法策略，找到的解是否一定是问题的最优解。
     *
     * 如果确定可以使用贪心算法，那一定要选择合适的贪心策略；
     */
    public static void main(String args[]) {
        greedyGiveMoney(99);
    }

    /**
     * 找零钱问题：
     * 假设1元、2元、5元、10元、20元、50元、100元的纸币，张数不限制，现在要用来支付K元，至少要多少张纸币？
     * 很显然，我们很容易就想到使用贪心算法来解决，并且我们所根据的贪心策略是，每一步尽可能用面值大的纸币即可。当然这是正确的，代码如下：
     * @param money
     */
    public static void greedyGiveMoney(int money) {
        System.out.println("需要找零: " + money);
        int[] moneyLevel = {1, 5, 10, 20, 50, 100};
        for (int i = moneyLevel.length - 1; i >= 0; i--) {
            int num = money / moneyLevel[i];
            int mod = money % moneyLevel[i];
            money = mod;
            if (num > 0) {
                System.out.println("需要" + num + "张" + moneyLevel[i] + "块的");
            }
        }
    }
}
