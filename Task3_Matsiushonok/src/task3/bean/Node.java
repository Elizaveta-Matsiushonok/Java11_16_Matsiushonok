package task3.bean;

public class Node {
	
	private NodeType nodeType;
	private String content;
	
	
	public Node(){
		
	}
	
	public Node(NodeType nodeType, String content){
		this.nodeType = nodeType;
		this.content = content;
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
