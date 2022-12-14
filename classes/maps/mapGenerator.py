for i in range(5):
    for j in range(5):
        for z in range(5):
            with open(f"./maps/map{i}{j}{z}.csv", "w") as file:
                file.write("""W,W,W,W,W,W,W,W,W,W
W,#,#,#,#,#,#,#,#,W
W,#,#,#,#,#,#,#,#,W
W,#,#,#,#,#,#,#,#,W
W,#,#,#,#,#,#,#,#,W
W,#,#,#,#,#,#,#,#,W
W,#,#,#,#,#,#,#,#,W
W,#,#,#,#,#,#,#,#,W
W,#,#,#,#,#,#,#,#,W
W,W,W,W,W,W,W,W,W,W
""")