
private int totalMoneyInWallet = 0;

private Integer getMoney(int money) {
    println(Thread.currentThread().getName() +  ": Khách hàng đưa " + money);
    totalMoneyInWallet += money;
    println(Thread.currentThread().getName() + ": Tổng tiền trong ví là " + totalMoneyInWallet + " Đồng | " + LocalTime.now());

    return money;
}

void main() {

    //Ưu điểm: Nhanh, tuần tự, tái sử dụng lại Thread được -> giảm tiêu thụ tài nguyên của CPU
    //Nhược điểm:
    // - Vẫn tranh giành truy cập vào một biến (totalMoneyInWallet)
    //           --> nếu có một số lượng lớn truy cập vào biến này sẽ gây ra không đồng bộ
    // - Deadlock:
    //     + Ông A và B muốn làm cafe sữa, nhưng:
    //          -  A có cafe, B thì có sữa, 2 ông vì cái tôi cao nên cứ đợi nhau
    //          -  A thì ngồi chờ sữa, B thì ngồi chờ cafe -> DEADLOCK


    //Giải pháp: Làm cho mỗi luồng(Thread) có một biến riêng (privateTotalMoneyWallet)
    //           --> Xuất hiện ThreadLocal

    ExecutorService threadPool = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 100; i++) {
        threadPool.submit(() -> {
            getMoney(new Random().nextInt(100));

            try {
                Thread.sleep(2* 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            getMoney(new Random().nextInt(100));
        });
    }

    threadPool.shutdown();;
}

