package foking.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private TreeNode rootNode;
    private int height = -1;
    private int depth = -1;

    public enum TRAVERSAL_TYPE {
        PREORDER, //前序遍历
        INORDER, //中序遍历
        POSTORDER //后序遍历
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * 插入数据
     *
     * @param value
     * @return
     */
    public boolean insert(int value) {
        try {
            if (rootNode == null) {
                rootNode = new TreeNode(value, null, null);
                return true;
            }
            insertNode(rootNode, value);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 插入
     *
     * @param parentNode 新节点
     */
    private void insertNode(TreeNode parentNode, int value) throws Exception {
        if (value < parentNode.getData()) {
            //如果节点的值比插入的值要小,则插入左子树
            if (parentNode.getLeftNode() == null) {
                TreeNode leftnode = new TreeNode(value, null, null);
                parentNode.setLeftNode(leftnode);
                return;
            }
            insertNode(parentNode.getLeftNode(), value);
        } else if (value >= parentNode.getData()) {
            //如果节点的值比插入的值要大，则插入右子树
            if (parentNode.getRightNode() == null) {
                TreeNode rightNode = new TreeNode(value, null, null);
                parentNode.setRightNode(rightNode);
                return;
            }
            insertNode(parentNode.getRightNode(), value);
        }
    }

    /**
     * 查询某个节点
     *
     * @param value 节点对应的值
     * @return
     */
    public List<TreeNode> quary(int value) {
        List<TreeNode> nodes = new ArrayList<>();
        quaryNode(rootNode, value, nodes);
        return nodes;
    }

    private List<TreeNode> quaryNode(TreeNode parentNode, int value, List<TreeNode> nodes) {
        //当前节点相等则添加到列表中
        if (parentNode.getData() == value) {
            nodes.add(parentNode);
            //右子树不为空
            if (parentNode.getRightNode() != null) {
                quaryNode(parentNode.getRightNode(), value, nodes);
            }
        } else if (value < parentNode.getData()) {
            //左子树不为空
            if (parentNode.getLeftNode() != null) {
                quaryNode(parentNode.getLeftNode(), value, nodes);
            }
        } else if (value > parentNode.getData()) {
            if (parentNode.getRightNode() != null) {
                quaryNode(parentNode.getRightNode(), value, nodes);
            }
        }
        return nodes;
    }

    public void delete2(int data) {
        TreeNode p = rootNode; // p 指向要删除的节点，初始化指向根节点
        TreeNode pp = null; // pp 记录的是 p 的父节点
        while (p != null && p.getData() != data) {
            pp = p;
            if (data > p.getData()) p = p.getRightNode();
            else p = p.getLeftNode();
        }
        if (p == null) return; // 没有找到

        // 要删除的节点有两个子节点
        if (p.getLeftNode() != null && p.getRightNode() != null) { // 查找右子树中最小节点
            TreeNode minP = p.getRightNode();
            TreeNode minPP = p; // minPP 表示 minP 的父节点
            while (minP.getRightNode() != null) {
                minPP = minP;
                minP = minP.getLeftNode();
            }
            p.setData(minP.getData()); // 将 minP 的数据替换到 p 中
            p = minP; // 下面就变成了删除 minP 了
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        TreeNode child; // p 的子节点
        if (p.getLeftNode() != null) child = p.getLeftNode();
        else if (p.getRightNode() != null) child = p.getRightNode();
        else child = null;

        if (pp == null) rootNode = child; // 删除的是根节点
        else if (pp.getLeftNode() == p) pp.setLeftNode(child);
        else pp.setRightNode(child);
    }


    /**
     * 删除某一个节点
     *
     * @param value
     * @return
     */
    public List<TreeNode> delete(int value) {
        List<TreeNode> alreadyDeleteNodes = new ArrayList<>();
        if (rootNode.getData() == value) {
            alreadyDeleteNodes.add(rootNode);
        } else if (value < rootNode.getData() && rootNode.getLeftNode() != null) {
            deleteNode(rootNode, rootNode.getLeftNode(), alreadyDeleteNodes, value, true);
        } else if (value > rootNode.getData() && rootNode.getRightNode() != null) {
            deleteNode(rootNode, rootNode.getRightNode(), alreadyDeleteNodes, value, false);
        }
        return alreadyDeleteNodes;
    }

    private List<TreeNode> deleteNode(TreeNode parentNode, TreeNode operateNode, List<TreeNode> alreadyDeleteNodes, int value, boolean isLeftNode) {

        //当前节点相等则添加到列表中
        if (operateNode.getData() == value) {

            //没有子节点
            if (operateNode.getLeftNode() == null && operateNode.getRightNode() == null) {
                alreadyDeleteNodes.add(operateNode);
                if (isLeftNode) {
                    parentNode.setLeftNode(null);
                } else {
                    parentNode.setRightNode(null);
                }
            }

            //左右子节点均有值
            if (operateNode.getRightNode() != null && operateNode.getLeftNode() != null) {

                //找到右子树中最小的，将该父节点的指向替换为右子树中最小节点，同时删除最小节点
                alreadyDeleteNodes.add(operateNode);

                TreeNode tempParentNode = operateNode;
                TreeNode childNode = operateNode.getRightNode();

                //最小节点的父节点
                while (childNode.getLeftNode() != null) {
                    tempParentNode = childNode;
                    childNode = childNode.getLeftNode();
                }

                //删除最小节点
                if (childNode.getRightNode() != null) {
                    tempParentNode.setLeftNode(childNode.getRightNode());
                } else {
                    tempParentNode.setLeftNode(null);
                }

                //将最小节点放置在删除的那个位置上
                childNode.setLeftNode(operateNode.getLeftNode());
                childNode.setRightNode(operateNode.getRightNode());
                if (isLeftNode) {
                    parentNode.setLeftNode(childNode);
                } else {
                    parentNode.setRightNode(childNode);
                }

                //设置结束之后继续删除
                deleteNode(childNode, childNode.getRightNode(), alreadyDeleteNodes, value, false);

            }

            //只有一个子节点,将子节点替换为删除的节点
            if (operateNode.getLeftNode() != null) {
                alreadyDeleteNodes.add(operateNode);
                if (isLeftNode) {
                    parentNode.setLeftNode(operateNode.getLeftNode());
                } else {
                    parentNode.setRightNode(operateNode.getLeftNode());
                }

            }
            if (operateNode.getRightNode() != null) {

                //如果右子节点的值也相等也是需要删除的
                if (operateNode.getRightNode().getData() == value) {
                    deleteNode(operateNode, operateNode.getRightNode(), alreadyDeleteNodes, value, false);
                } else {
                    if (isLeftNode) {
                        parentNode.setLeftNode(operateNode.getRightNode());
                    } else {
                        parentNode.setRightNode(operateNode.getRightNode());
                    }
                }
            }
        } else if (value < operateNode.getData()) {
            if (operateNode.getLeftNode() != null) {
                deleteNode(operateNode, operateNode.getLeftNode(), alreadyDeleteNodes, value, true);
            }
        } else {
            if (operateNode.getRightNode() != null) {
                deleteNode(operateNode, operateNode.getRightNode(), alreadyDeleteNodes, value, false);
            }
        }
        return alreadyDeleteNodes;
    }

    /**
     * 遍历二叉树
     *
     * @param type 通过什么方式
     */
    public void iterator(TRAVERSAL_TYPE type) {
        switch (type) {
            case INORDER:
                if (rootNode != null) {
                    traversalNode(rootNode);
                }
                break;
        }
    }

    /**
     * 遍历节点
     *
     * @param node
     */
    private void traversalNode(TreeNode node) {
        //先遍历左子树
        if (node.getLeftNode() != null) {
            traversalNode(node.getLeftNode());
        }
        //遍历节点
        System.out.print(node.getData() + ",");

        //遍历右子树
        if (node.getRightNode() != null) {
            traversalNode(node.getRightNode());
        }
    }

}
