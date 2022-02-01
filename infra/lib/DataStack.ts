import { Stack, StackProps } from 'aws-cdk-lib';
import { Construct } from 'constructs';
import { ManagedPolicy, Role, User } from 'aws-cdk-lib/aws-iam';
import { AttributeType, ProjectionType, Table } from 'aws-cdk-lib/aws-dynamodb';

export class DataStack extends Stack {
	readonly USER_ARN = 'arn:aws:iam::709312312393:user/adamtrev-cli';
	readonly DYNAMO_DB_FULL_ACCESS_POLICY = 'arn:aws:iam::aws:policy/AmazonDynamoDBFullAccess';

	readonly TABLE_NAME = 'TestbedTable';

	constructor(scope: Construct, id: string, props?: StackProps) {
		super(scope, id, props);

		this.createRole();
		this.createTable();
	}

	createRole() {
		const dynamoRole = new Role(
			this,
			'DynamoRole',
			{
				assumedBy: User.fromUserArn(this, 'adamtrev-cli', this.USER_ARN)
		});

		dynamoRole.addManagedPolicy(ManagedPolicy.fromManagedPolicyArn(
			this,
			'DynamoManagedPolicy',
			this.DYNAMO_DB_FULL_ACCESS_POLICY
		));
	}

	createTable() {
		const table = new Table(
			this,
			this.TABLE_NAME,
			{
				partitionKey: {
					name: 'pk',
					type: AttributeType.STRING
				},
				sortKey: {
					name: 'sk',
					type: AttributeType.STRING
				}
		});

		table.addGlobalSecondaryIndex({
			indexName: 'gsi1',
			partitionKey: {name: 'gsi1-pk', type: AttributeType.STRING},
			sortKey: {name: 'gsi1-sk', type: AttributeType.STRING},
			projectionType: ProjectionType.ALL
		});
	}
}
