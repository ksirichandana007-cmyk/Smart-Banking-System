class Node {
    int accountNo, height;
    Node left, right;

    Node(int accountNo) {
        this.accountNo = accountNo;
        height = 1;
    }
}

public class SmartBankingCO1 {

    Node root;

    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int accountNo) {

        if (node == null)
            return new Node(accountNo);

        if (accountNo < node.accountNo)
            node.left = insert(node.left, accountNo);
        else if (accountNo > node.accountNo)
            node.right = insert(node.right, accountNo);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && accountNo < node.left.accountNo)
            return rightRotate(node);

        if (balance < -1 && accountNo > node.right.accountNo)
            return leftRotate(node);

        if (balance > 1 && accountNo > node.left.accountNo) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && accountNo < node.right.accountNo) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    boolean search(Node root, int key) {
        if (root == null)
            return false;

        if (root.accountNo == key)
            return true;

        if (key < root.accountNo)
            return search(root.left, key);

        return search(root.right, key);
    }

    public static void main(String[] args) {

        SmartBankingCO1 bank = new SmartBankingCO1();

        int accounts[] = {
            5001, 3001, 7001,
            2001, 4001, 6001, 8001
        };

        for (int acc : accounts)
            bank.root = bank.insert(bank.root, acc);

        int searchAccount = 6001;

        if (bank.search(bank.root, searchAccount))
            System.out.println("Account Found");
        else
            System.out.println("Account Not Found");
    }
}