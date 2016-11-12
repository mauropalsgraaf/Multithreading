package data_structures.implementation;

class FilterLock implements Lock {
//    int[] level;
//    int[] victim;

    public FilterLock(int n) {
//        level = new int[n];
//        victim = new int[n]; // use 1..n-1
//        for (int i = 0; i < n; i++) {
//            level[i] = 0;
//        }
    }

    public void lock() {
//        int me = (int) Thread.currentThread().getId();
//        for (int i = 1; i < n; i++) { //attempt level 1
//            level[me] = i;
//            victim[i] = me;
//            // spin while conflicts exist
//            while ((∃k != me)(level[k] >= i && victim[i] == me)){
//            }
//            ;
//        }
    }

    public void unlock() {
//        int me = (int) Thread.currentThread().getId();
//        level[me] = 0;
    }
}
