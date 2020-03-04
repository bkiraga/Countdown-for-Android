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

        public fun toStr() :String {
            return "char: $character, isWord: $isWord\nchildren: $children"
        }

    }

    class Trie {

        var size = 0
        var root: Node = Node("")

        public fun add_word(word: String) {
            Log.d(TAG, "ADDING $word")
            var node = root
            for (char: Char in word.trim()) {
                if (!node.children.keys.contains(char.toString())) {
                    node.children[char.toString()] = Node(char.toString())
                    node.character = char.toString()
                }
                //Log.d(TAG, "TRIE is: ${node.toStr()}\n")

                node = node.children[char.toString()]!!
            }
            node.isWord = true
            size += 1
            //Log.d(TAG, "END - TRIE is: ${node.toStr()}\n")
        }

        public fun getTrieSize(): Int {
            return size
        }

        public fun trieToString(): String {
            return root.children.toString()
        }

        public fun search_word(word: String): Boolean {
            Log.d(TAG, "Searching for: $word")
            var node = root
            for (char: Char in word) {
                if (!node.children.containsKey(char.toString())) {
                    return false
                }
                //Log.d(TAG, "Trie is ${node.children}")

                node = node.children[char.toString()]!!
                //Log.d(TAG, node.toStr())
                //Log.d(TAG, "terminal: ${node.isWord}")

            }
            Log.d(TAG, "Search:$word is present: ${node.isWord}\n")
            return node.isWord
        }

    companion object {
        private val TAG = "LettersSolver"
    }
    }
}