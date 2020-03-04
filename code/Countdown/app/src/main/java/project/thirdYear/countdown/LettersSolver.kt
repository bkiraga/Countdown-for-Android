package project.thirdYear.countdown
import android.util.Log
import java.io.File
import android.content.Context
import android.nfc.Tag

class LettersSolver {

    class Node (var char:String){

        var character:String = ""
        var children:HashMap<String, Node> = HashMap(26)
        var isWord:Boolean = false

    }

    class Trie {

        var size = 0
        var root: Node = Node("")

        public fun add_word(word: String) {
            Log.d(TAG, "ADDING $word")
            var node = root
            for (char: Char in word) {
                if (!node.children.keys.contains(char.toString())) {
                    node.children[char.toString()] = Node(char.toString())
                    node.character = char.toString()
                    //Log.d(TAG, "ADDED $char")
                }
                //Log.d(TAG, "TRIE is: ${node.character}\n")

                node = node.children[char.toString()]!!
            }
            node.isWord = true
            size += 1
        }

        public fun getTrieSize(): Int {
            return size
        }

        public fun trieToString(): String {
            return root.children.toString()
        }

        public fun search_word(word: String): Boolean {
            var node = root
            for (char: Char in word) {
                if (!node.children.containsKey(char.toString())) {
                    return false
                }
                node = node.children[char.toString()]!!
            }
            return node.isWord
        }

    companion object {
        private val TAG = "LettersSolver"
    }
    }
}