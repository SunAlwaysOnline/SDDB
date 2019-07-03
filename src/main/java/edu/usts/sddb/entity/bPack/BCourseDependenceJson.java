package edu.usts.sddb.entity.bPack;

import java.util.List;

//大数据分析中课程预警生成的json文件对应的实体类

public class BCourseDependenceJson {

    //节点类数组
    private List<Node> nodes;

    //线条类数组
    private List<Edge> edges;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "BCourseDependenceJson{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}




