
# 首页轮播图选择
SELECT 
name, picture, url, weight, enable, create_time, create_user, update_time, update_user, del
FROM t_consts_site_carousel
WHERE enable = 1
ORDER BY weight DESC
LIMIT 1, 3

