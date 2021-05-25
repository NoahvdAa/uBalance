package me.noahvdaa.ubalance.utils;

import me.noahvdaa.ubalance.model.TargetServer;

import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class RandomUtils {

	public static TargetServer pickServer(List<TargetServer> input) {
		TreeMap<Integer, TargetServer> sortMap = new TreeMap<>();
		int total = 0;
		for (TargetServer ts : input) {
			sortMap.put(total, ts);
			total += ts.getWeight();
		}
		Random rand = new Random();
		int num = rand.nextInt(total);
		return sortMap.get(sortMap.floorKey(num));
	}

}
