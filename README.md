
# Big-Data-Analytics-on-Amazon-Customer-Reviews

Analyzed Amazon customer reviews using Hadoop MapReduce, with chaining, secondary sorting, joins, binning, summarization, and filtration patterns. Utilized Apache Pig and Mahout for recommendations.

## Abstract:
This project analyzed Amazon Camera reviews to gain insights using Hadoop, Mahout, and Pig.

## Dataset:
### Dataset Link:
[Amazon Reviews - Camera](https://s3.amazonaws.com/amazon-reviews-pds/tsv/amazon_reviews_us_Camera_v1_00.tsv.gz)

### Accessing the Data:
Dataset can be accessed via [this link](https://s3.amazonaws.com/amazon-reviews-pds/readme.html).

### Data Format:
Tab-separated text file (1GB), with columns: marketplace, customer_id, review_id, product_id, product_parent, product_title, product_category, star_rating, helpful_votes, total_votes, vine, verified_purchase, review_headline, review_body, review_date.

## Data Analysis:
1. Total products in dataset
2. Average product rating
3. TopN reviewed products by count
4. Total ratings for TopN reviewed products
5. Users who reviewed each product
6. Records partitioned by review date
7. Product info by star rating in bins
8. Recommend products based on star rating
9. Review count grouped by date per product
10. Product count for each star rating

## Technologies:
- Hadoop MapReduce (Summarization, Joins, Secondary Sorting, Chaining, Filtering)
- Mahout (Recommendation)
- Apache Pig

## Additional Notes:
- Dataset from Amazon S3 via AWS-CLI
- Log4j logging
- MultipleInputs for Reduce Side Join
- MultipleOutputs for Binning
- Custom Partitioner and WritableComparator for sorting
- MapReduce Chaining
- Ran Pig script locally via grunt shell