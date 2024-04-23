package week8;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Baiyu
 * @Time 2024/3/14 15:55
 * @StudentNumber 2018217662
 * @Description
 */


class Node {
    int val;
    Node next;
    Node random;
    
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Solution5 {
    // 复制带随机指针的链表
    //思路1
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        //1. 创建一个新的链表作为结果
        Node retHead = new Node(0);
        Node cur = retHead;
        //2. 遍历参数链表，复制一个一摸一样的链表
        Node temp = head;
        while (temp != null) {
            cur.val = temp.val;
            if (temp.next != null) {
                cur.next = new Node(0);
            } else {
                cur.next = null;
            }
            cur = cur.next;
            temp = temp.next;
        }
        //4. 遍历参数链表，复制随机指针
        Node temp2 = head;
        temp = retHead;
        while (temp2 != null) {
            if (temp2.random != null) {
                //从原始节点的头部向后找到这个随机的节点
                Node tempHead = head;
                Node tempRet = retHead;
                //原始指针和新指针同时向后移动，直到找到这个随机的节点
                while (tempHead != temp2.random) {
                    tempHead = tempHead.next;
                    tempRet = tempRet.next;
                }
                temp.random = tempRet;
            } else {
                temp.random = null;
            }
            temp2 = temp2.next;
            temp = temp.next;
        }
        return retHead;
    }
    
    
    //思路2
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        //1. 创建一个哈希表,捆绑原始节点和新节点，这样就可以通过原始节点的random指向的节点找到新节点
        //   ——键是原始节点，值是新节点
        HashMap<Node, Node> bundle = new HashMap<>();
        Node temp = head;
        //2. 先初始化bundle,这样就相当于把原始链表和新链表的对应节点捆绑在一起
        while (temp != null) {
            bundle.put(temp, new Node(temp.val));
            temp = temp.next;
        }
        //3. 重新遍历原始链表，更新新链表的next和random指针
        temp = head;
        while (temp != null) {
            //更新新链表的next指针——因为bundle中已经捆绑了原始节点和新节点，所以可以直接通过原始节点找到新节点
            //bundle.get(head)是新链表的节点，bundle.get(head.next)是新链表的下一个节点
            //bundle.get(head.next)相当于从原始节点的next指针找到捆绑的新节点的next
            bundle.get(temp).next = bundle.get(temp.next);
            //更新新链表的random指针
            //bundle.get(head)是新链表的节点，bundle.get(head.random)是新链表的随机节点
            //bundle.get(head.random)相当于从原始节点的random指针找到捆绑的新节点的random
            //因为random指向的引用已经在bundle中，而对应的bundle的值就是新链表当前random应该指向的节点
            bundle.get(temp).random = bundle.get(temp.random);
            temp = temp.next;
        }
        //4. 返回新链表的头部
        return bundle.get(head);
    }
    
    //思路3
    //创建一个哈希表，存储已经复制过的节点
    Map<Node, Node> cachedNode = new HashMap<Node, Node>();
    
    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }
        //如果已经复制过这个节点，直接返回
        if (!cachedNode.containsKey(head)) {
            //复制当前节点
            Node headNew = new Node(head.val);
            //存储复制过的节点
            cachedNode.put(head, headNew);
            //复制next和random指针
            //next
            headNew.next = copyRandomList3(head.next);
            headNew.random = copyRandomList3(head.random);
        }
        //返回复制过的节点
        return cachedNode.get(head);
    }
    
    //思路4
    public Node copyRandomList4(Node head) {
        if (head == null) {
            return null;
        }
        //1. 遍历原始链表，将新节点插入到原始节点的后面——每次插入一个新节点，新节点的next指向原始节点的next
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = new Node(node.val);
            nodeNew.next = node.next;
            node.next = nodeNew;
        }
        //2. 遍历原始链表，更新新节点的random指针
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = node.next;
            //如果原始节点的random指针不为空，新节点的random指针指向原始节点的random指针的next
            //也就是新节点的random指针指向原始节点的random指针指向的新节点
            nodeNew.random = (node.random != null) ? node.random.next : null;
        }
        //3. 创建一个新的链表作为结果，将新节点从原始链表中分离出来
        Node headNew = head.next;
        for (Node node = head; node != null; node = node.next) {
            //nodeNew指向原始节点的next指针——也就是新节点
            Node nodeNew = node.next;
            //原始节点的next指针指向原始节点的next指针的next指针——也就是原始节点的下一个原始节点
            //这样在node.next时就能直接跳到下一个原始节点
            node.next = node.next.next;
            //新节点的next指针指向新节点的next指针的next指针——也就是新节点的下一个新节点
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        //4. 返回新链表的头部
        return headNew;
    }
}
