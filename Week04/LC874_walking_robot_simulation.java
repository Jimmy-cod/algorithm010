package Week04;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class LC874_walking_robot_simulation {
/*
*
int direx[] = {0,1,0,-1};
int direy[] = {1,0,-1,0};
direx[],direy[] 要竖着对齐看
    - 向北，坐标轴上x不动，y+1, 即(0,1)
    - 向东，坐标轴上x+1，y不动, 即(1,0)
    - 向南，坐标轴上x不动，y-1, 即(0,-1)
    - 向西，坐标轴上x-1，y不动, 即(-1,0)
 走( direx[i], direy[i] )，加上当前坐标后为 (curx,cury) + ( direx[i], direy[i] )
direx[]direy[] 的下标 i 代表了当前机器人的方向

i=0,向北
i=1,向东
i=2,向南
i=3,向西
当读取到调整方向的指令时，如

"-1"：“向右转90度”，只要当前方向curdire + 1就可以得到右转方向
"-2"：“向左转90度”，只要当前方向curdire + 3 就可以得到左转方向 (curdire + 3) % 4，
因为不管curdire当前是哪个方向，左转都在其左边，在direx数组的定义中顺势针数3个就是其左边，所以就是加3

* */
    public int robotSim(int[] commands, int[][] obstacles) {
        int direx[] = {0,1,0,-1};
        int direy[] = {1,0,-1,0};
        int curx=0,cury=0;
        int curdire = 0;
        int ans = 0;
        Set<Pair<Integer,Integer>> obstacleSet = new HashSet<>();
        for (int i = 0; i< obstacles.length; i++){
            obstacleSet.add(new Pair<>(obstacles[i][0],obstacles[i][1]));
        }

        for (int command : commands){
            if (command == -1) { // -1：向右转 90 度
                curdire = (curdire +1 ) %4;
            }else if (command == -2){ // -2：向左转 90 度
                curdire = (curdire +3 ) %4;
            }else { //向前移动 x 个单位长度
                for (int j =0;j<command;j++){
                    //试图走出一步，并判断是否遇到了障碍物
                    int nx = curx + direx[curdire];
                    int ny = cury + direy[curdire];
                      //当前坐标不是障碍点，计算并与存储的最大欧式距离的平方做比较
                    if (!obstacleSet.contains(new Pair<>(nx,ny))){
                        curx = nx;
                        cury = ny;
                        ans = Math.max(ans,curx*curx + cury*cury);
                    }else {
                        //是障碍点，被挡住了，停留，只能等待下一个指令
                        break;
                    }
                }
            }

        }
        return ans;
    }

}
