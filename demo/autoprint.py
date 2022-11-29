# automated generate necessary names.
# for i in range(1, 21):
# #     # print("int[] predicted%02d = {%d, %d, %d, %d};" %(i, i/8, (i/4)%2, (i/2)%2, i%2))
#     # print("Arguments.of(rootPath + \"test%02d.java\", predicted%02d)," %(i, i))
#     print("int[] predicted%02d = " %(i))


#automated generate empty java docs
# import pathlib 
# total_file_name = "demo\\test_code\\equivalence_class_test\\test" 
# file_name = []
# for i in range(20, 21):
#     name = total_file_name + str(i).zfill(2) + ".java"
#     file_name.append(name)
# print(file_name)
# for file in file_name:
#     pathlib.Path(file).touch()


# automated walk folder.
import os
path = "C:\\Users\\SubFunction Residue\\Documents\\AAA My Scripts\\Java Scripts\\221106_codeCount2\\demo\\test_code\\equivalence_class_test"
for root, dirs, files in os.walk(path, topdown=False):
    for i, name in enumerate(files):
        print("<!--code of test%02d.java-->" %(i+1))
        f = open(os.path.join(root, name),'rb')
        byt = f.readlines()
        for line in byt:
            print(line)
        print("\n")
