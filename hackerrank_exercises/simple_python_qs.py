def remove_duplicates(nums):
    seen = set()
    result = []
    
    for n in nums:
        if n not in seen:
            seen.add(n)
            result.append(n)
    
    return result


#Reverse singly linked list
class Node:
    def __init__(self, val, next=None):
        self.val = val
        self.next = next

def reverse_list(head):
    prev = None
    curr = head
    
    while curr:
        nxt = curr.next
        curr.next = prev
        prev = curr
        curr = nxt
    
    return prev

#Maximum depth of binary tree
class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def max_depth(root):
    if root is None:
        return 0
    
    left = max_depth(root.left)
    right = max_depth(root.right)
    
    return 1 + max(left, right)

#Two sum
def two_sum(nums, target):
    seen = {}
    
    for i in range(len(nums)):
        needed = target - nums[i]
        
        if needed in seen:
            return [seen[needed], i]
        
        seen[nums[i]] = i
    
    return None
