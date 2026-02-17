def remove_duplicates(nums):
    seen = set()
    result = []
    for n in nums:
        if n not in seen:
            seen.add(n)
            result.append(n)
    return result

#input array of numbers
def remove_duplicates(nums):
    #set has no multiplicity so tracks unique elements only
    seen = set()
    #initialise empty array
    result = []
    
    #python lets us iterative over array directly, no need for index
    for n in nums:
        #"not in" syntax lets us check if element is in set, O(1) time complexity
        if n not in seen:
            #.add adds element to set
            seen.add(n)
            #append adds the (previously) unseen element to result array
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

# Define a node for a singly linked list
class node:
    #__init__ is called when an object is created, 
    #here it initialises the value and next pointer
    def __init__(self, val, next=None):
        #self refers to the instance of the node being created
        #val is the value stored in the node, next is
        # reference to the next node in the list (default is None)
        self.val = val      # store the value - the period is used to access the instance variable val
        self.next = next    # store the reference to the next node
        #instance variables are also know as attributes, they hold the data for each node


def reverse_list(head):
    prev = None   # this will become the new head
    curr = head   # start at the original head
    
    # traverse the list
    while curr:
        nxt = curr.next   # save next node
        curr.next = prev  # reverse the pointer
        prev = curr       # move prev forward
        curr = nxt        # move curr forward

    # prev is now the new head of the reversed list
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

#Just some SQL, top three salaries per dept
#SELECT *
#FROM (
#    SELECT
#        employee_id,
#        department_id,
#        salary,
#        ROW_NUMBER() OVER (
#            PARTITION BY department_id
#            ORDER BY salary DESC
#        ) AS rn
#    FROM employees
#) t
#WHERE rn <= 3;


def two_sum_v2(nums, target):
    """
    Use a hashmap to store value -> index seen so far.
    For each number, check if complement already seen.
    """
    seen = {}  # value -> index

    for i, num in enumerate(nums):
        complement = target - num

        if complement in seen:
            return [seen[complement], i]  # found pair

        seen[num] = i  # store current value and index

    return None  # no solution found

def merge_sorted_lists(a, b):
    """
    Classic two-pointer traversal.
    """
    i = j = 0
    result = []

    while i < len(a) and j < len(b):
        if a[i] < b[j]:
            result.append(a[i])
            i += 1
        else:
            result.append(b[j])
            j += 1

    # Append remaining elements
    result.extend(a[i:])
    result.extend(b[j:])

    return result

def binary_search(nums, target):
    """
    Classic iterative binary search.
    """
    left, right = 0, len(nums) - 1

    while left <= right:
        mid = (left + right) // 2

        if nums[mid] == target:
            return mid
        elif nums[mid] < target:
            left = mid + 1
        else:
            right = mid - 1

    return -1  # not found
