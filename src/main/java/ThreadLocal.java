
private ThreadLocal<Integer> totalMoneyInWallet = new ThreadLocal<Integer>() {

    @Override
    protected Integer initialValue() {
        return 0;
    }
};


private Integer getMoney(int money) {
    println(Thread.currentThread().getName() +  ": Khách hàng đưa " + money);
    int currentMoney = totalMoneyInWallet.get();
    totalMoneyInWallet.set(currentMoney + money);
    println(Thread.currentThread().getName() + ": Tổng tiền trong ví là " + totalMoneyInWallet.get() + " Đồng | " + LocalTime.now());

    return money;
}

void main() {

    // ==> Mỗi Thread sẽ có một ví tiền riêng và tự quản lý nó, khi nào muốn lấy thì get(), thêm tiền thì set()

    //Nhược điểm: gây rò rỉ bộ nhớ, khi không hủy ThreadLocal,...
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

