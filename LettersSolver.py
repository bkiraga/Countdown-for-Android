from time import time
from nltk.corpus import words
import sys

class Node():
    
    def __init__(self, character, word=None):
        self.character = character
        self.children = {}
        self.word = word

    def __str__(self):
        return "Character: {} - children: {} - isWord: {}".format(self.character, self.children, self.word)


class Trie():

    def __init__(self):
        self.root = Node("")

    def add_word(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                node.children[char] = Node(char)
            node = node.children[char]
        node.word = True
    
    def search_word(self, word):
        present = False
        node = self.root
        for char in word:
            if char not in node.children:
                return present
            node = node.children[char]
        return node.word


def main():
    
    t = Trie()
    for i in words.words():
        if len(i) > 2 and len(i) < 10:
            t.add_word(i.upper())
    print(sys.getsizeof(t))
    t.add_word("ENGINEERS")
    t.add_word("ENGINEERING")
    print(t.search_word("ENGINEERS"))
    print(t.search_word("ENGINEERING"))
    
if __name__ == "__main__":
    main()