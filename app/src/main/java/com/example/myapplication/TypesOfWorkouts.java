package com.example.myapplication;

import java.util.ArrayList;
import java.util.TreeMap;

public class TypesOfWorkouts {

    public TypesOfWorkouts() {
    }

    public TreeMap<String, Integer> Chest() {
        TreeMap<String, Integer> chest_workout =  new TreeMap<>();  // (운동명, 세트 갯수)
        chest_workout.put("덤벨 프레스 (8 ~ 12회 * 6세트)", 6);
        chest_workout.put("인클라인 덤벨 프레서 (8 ~ 12회 * 5세트)",5);
        chest_workout.put("덤벨 플라이 (15회 * 4세트)", 4);
        chest_workout.put("팔 굽혀 펴기 (최대한 많이 * 5세트)", 5);
        return chest_workout;
    }

    public TreeMap<String, Integer> Shoulder(){
        TreeMap<String, Integer> shoulder_workout = new TreeMap<>();
        shoulder_workout.put("숄더 프레스 (8 ~ 12회 * 5세트)", 5);
        shoulder_workout.put("사이드 레터럴 레이즈 (최대한 많이 * 6세트)", 6);
        shoulder_workout.put("후면 레이즈 (최대한 많이 *5세트)", 5);
        shoulder_workout.put("프런트 레이즈 (최대한 많이 * 5세트)", 5);
        return shoulder_workout;
    }

    public TreeMap<String, Integer> Back(){
        TreeMap<String, Integer> back_workout = new TreeMap<>();
        back_workout.put("턱걸이 (최대한 많이 * 5세트)", 5);
    back_workout.put("덤벨 데드리프트 (12 ~ 15회 * 5세트", 5);
        back_workout.put("실 로우 (12 ~ 15회 * 5세트)", 5);
        back_workout.put("원 암 로우 (12회 *5세트)", 5);
        return back_workout;
    }

    public TreeMap<String, Integer> Leg(){
        TreeMap<String, Integer> leg_workout = new TreeMap<>();
        leg_workout.put("덤벨 스쿼트", 5);
        leg_workout.put("덤벨 런지", 5);
        leg_workout.put("맨몸 스쿼트", 5);
        leg_workout.put("점프 스쿼트", 5);
        return leg_workout;
    }

    public TreeMap<String, Integer> Arm(){
        TreeMap<String, Integer> arm_workout = new TreeMap<>();
        arm_workout.put("이두 컬 (12회 * 3세트)", 3);
        arm_workout.put("삼 방향 이두 컬 (8 ~ 12회 * 4세트)", 4);
        arm_workout.put("삼두 익스텐션 (12회 * 5세트)", 5);
        return arm_workout;
    }

    public TreeMap<String, Integer> Abs(){
        TreeMap<String, Integer> abs_workout = new TreeMap<>();
        abs_workout.put("복근 크런치 (30회 * 3세트)", 3);
        abs_workout.put("레그 레이즈 (20회 * 5세트)", 5);
        abs_workout.put("브이 업 (15회 * 3세트)", 3);
        return abs_workout;
    }

    public TreeMap<String, Integer> Running(){
        TreeMap<String, Integer> run_workout = new TreeMap<>();
        run_workout.put("5km 런 OR 사이클 30분", 1);
        return run_workout;
    }
}
