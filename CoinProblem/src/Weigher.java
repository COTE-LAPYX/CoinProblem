import java.util.List;

public class Weigher {
    public WeightTypeEnum weight(List<Object> objects1, List<Object> objects2) {
        if (getTotalWeight(objects1) > getTotalWeight(objects2)) return WeightTypeEnum.LEFT;

        if (getTotalWeight(objects1) < getTotalWeight(objects2)) return WeightTypeEnum.RIGHT;

        return WeightTypeEnum.EQUALS;
    }

    public WeightTypeEnum weight(Object objects1, Object objects2) {
        if (objects1.getWeight() > objects2.getWeight()) return WeightTypeEnum.LEFT;

        if (objects1.getWeight() < objects2.getWeight()) return WeightTypeEnum.RIGHT;

        return WeightTypeEnum.EQUALS;
    }

    public int getTotalWeight(List<Object> objects) {
        int totalWeight = 0;

        for (Object object : objects)
            totalWeight += object.getWeight();

        return totalWeight;
    }
}
