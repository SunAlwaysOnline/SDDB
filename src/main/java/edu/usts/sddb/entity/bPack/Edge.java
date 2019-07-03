package edu.usts.sddb.entity.bPack;

//课程依赖图的线条类
public class Edge {

    //源节点唯一标识
    private String sourceID;

    //线条属性(暂时无用)
    private String attributes;

    //目的节点唯一标识
    private String targetID;

    //线条大小(暂时无用)
    private Integer size;

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getTargetID() {
        return targetID;
    }

    public void setTargetID(String targetID) {
        this.targetID = targetID;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "sourceID='" + sourceID + '\'' +
                ", attributes='" + attributes + '\'' +
                ", targetID='" + targetID + '\'' +
                ", size=" + size +
                '}';
    }
}