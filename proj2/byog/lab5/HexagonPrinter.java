public class HexagonPrinter {

    public static void main(String[] args) {
        int size = 7; // 正六边形的高度（包括基线），同时假设宽度也是基于此的奇数
        int spacesBefore = (size - 1) / 2; // 每行星号前的空格数
        int starsPerRow = 1; // 每行的星号数，从1开始，到顶部后减少

        // 上半部分（包括顶部）
        for (int i = 0; i < size / 2 + 1; i++) {
            // 打印每行星号前的空格
            for (int j = 0; j < spacesBefore - i; j++) {
                System.out.print(" ");
            }
            // 打印星号
            for (int k = 0; k < starsPerRow; k++) {
                System.out.print("*");
            }
            // 为下一行准备，如果是顶部则星号数不增加，否则每行增加两个星号
            starsPerRow += (i < size / 2) ? 2 : 0;
            // 换行
            System.out.println();
        }

        // 下半部分（不包括已经打印的顶部）
        for (int i = size / 2 - 1; i >= 0; i--) {
            // 打印每行星号前的空格
            for (int j = 0; j < spacesBefore - i; j++) {
                System.out.print(" ");
            }
            // 打印星号
            starsPerRow -= 2; // 逐行减少星号数
            for (int k = 0; k < starsPerRow; k++) {
                System.out.print("*");
            }
            // 换行
            System.out.println();
        }
    }
}