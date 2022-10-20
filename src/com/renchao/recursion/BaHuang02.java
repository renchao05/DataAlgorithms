package com.renchao.recursion;

public class BaHuang02 {
	private static int num = 0;

	public static void main(String[] args) {
		int[] arr = {88, 88, 88, 88, 88, 88, 88, 88};
		int i = 0;
		int val = 0;


		HuangHou(arr,i,val);

	}

	public static void HuangHou(int[] che, int i, int val) {
		int temp = 0;
		if(i > 7) {
			//如果i大于7，表示找到一个解，打印输出
			num++;
			for(int k = 0; k < 8; k++){
				System.out.print(che[k] + " ");
			}
			System.out.print("\n");
			//得到一个解后，进行回溯。
			temp = che[i-1] + 1;
			che[i-1] = 88;
			HuangHou(che, i - 1, temp);

		} else if(i == 0) {
			//在第一行，如果val小于等于7，直接放皇后。如果大于7，说明全部回溯完成。
			if(val <= 7){
				//如果是第一行。直接放放皇后；
				che[i] = val;
				HuangHou(che, i + 1, 0);
			} else {
				System.out.print("所有解都找到,总共：" + num);
			}

		} else {
			//判断列在8以内。
			if(val < 8) {
				//循环判断与前面放的有没有冲突的
				boolean jud = true;
				for(int k = 0; k < 8; k++){
					if(val == che[k] || (val - i + k) == che[k] || (val + i - k) == che[k]) {
						jud = false;
						break;
					} else {
						//如果没有冲突的，把皇后放上，并且进行下一行判断。
						jud = true;
					}
				}

				if(jud) {
					che[i] = val;
					HuangHou(che, i + 1, 0);
				} else {
					HuangHou(che, i, val + 1);
				}
			} else {
				//如果下面一行全部有冲突，回到上面一行进行回溯；
				temp = che[i-1] + 1;
				che[i-1] = 88;
				HuangHou(che, i - 1, temp);
			}
		}
	}
}