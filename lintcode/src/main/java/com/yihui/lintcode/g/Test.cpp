class Solution {
public:
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    vector<string> split(const string &str, string delim) {
        vector<string> results;
        int lastIndex = 0, index;
        while ((index = str.find(delim, lastIndex)) != string::npos) {
            results.push_back(str.substr(lastIndex, index - lastIndex));
            lastIndex = index + delim.length();
        }
        if (lastIndex != str.length()) {
            results.push_back(str.substr(lastIndex, str.length() - lastIndex));
        }
        return results;
    }
    string serialize(TreeNode *root) {
        if (root == NULL) {
            return "{}";
        }
        vector<TreeNode *> q;
        q.push_back(root);
        for(int  i = 0; i < q.size(); i++) {
            TreeNode * node = q[i];
            if (node == NULL) {
                continue;
            }
            q.push_back(node->left);
            q.push_back(node->right);
        }
        while (q[q.size() - 1] == NULL) {
                q.pop_back();
        }
        string sb="";
        sb += "{";
        sb += to_string(q[0]->val);
        for (int i = 1; i < q.size(); i++) {
            if (q[i] == NULL) {
                sb += (",#");
            }
            else {
                sb += ",";
                sb += to_string(q[i]->val);
            }
        }
        sb += "}";
        return sb;
    }
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    TreeNode * deserialize(string &data) {
        // write your code here
        if (data == "{}") return NULL;
        vector<string> vals = split(data.substr(1, data.size() - 2), ",");
        TreeNode *root = new TreeNode(atoi(vals[0].c_str()));
        queue<TreeNode *> Q;
        Q.push(root);
        bool isLeftChild= true;
        for (int i = 1; i < vals.size(); i++) {
            if (vals[i] != "#") {
                TreeNode *node = new TreeNode(atoi(vals[i].c_str()));
                if (isLeftChild) Q.front()->left = node;
                else Q.front()->right = node;
                Q.push(node);
            }
            if (!isLeftChild) {
                Q.pop();
            }
            isLeftChild = !isLeftChild;
        }
        return root;
    }
};
