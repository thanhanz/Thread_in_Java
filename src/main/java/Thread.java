
    private int totalMoneyInWallet = 0;

    private synchronized Integer getMoney(int money) {
        println(Thread.currentThread().getName() +  ": Khách hàng đưa " + money);
        totalMoneyInWallet += money;
        println(Thread.currentThread().getName() + ": Tổng tiền trong ví là " + totalMoneyInWallet + " Đồng | " + LocalTime.now());

        return money;
    }

    void main() { //Làm tuần tự, Thread này chờ Thread trước hoàn thành (Synchronized)
                  // --> Hệ thống chậm, nặng và không thể TÁI SỬ DỤNG lại Thread

        Thread casher1 = new Thread(() -> {
            //Customer 1
            getMoney(new Random().nextInt(100));
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Customer 2
            getMoney(new Random().nextInt(100));

            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Customer 3
            getMoney(new Random().nextInt(100));

            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Customer 4
            getMoney(new Random().nextInt(100));

        }, "cashier1");
        casher1.start();

        Thread casher2 = new Thread(() -> {
            //Customer 1
            getMoney(new Random().nextInt(100));
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Customer 2
            getMoney(new Random().nextInt(100));

            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Customer 3
            getMoney(new Random().nextInt(100));

            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Customer 4
            getMoney(new Random().nextInt(100));

        }, "cashier2");
        casher2.start();

    }

