package edu.usts.sddb.entity.bPack;

//课程依赖图的节点类
public class Node {

    //节点颜色
    private String color;

    //节点文字
    private String label;

    //节点属性(暂时无用)
    private String attributes;

    //节点位移标识
    private String id;

    //节点大小
    private Integer size;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Node{" +
                "color='" + color + '\'' +
                ", label='" + label + '\'' +
                ", attributes='" + attributes + '\'' +
                ", id='" + id + '\'' +
                ", size=" + size +
                '}';
    }
}

